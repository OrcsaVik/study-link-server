/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exam.dto.AiGenerateRequestDto;
import com.exam.dto.AiPaperDto;
import com.exam.dto.QuestionImportDto;
import com.exam.dto.RuleDto;
import com.exam.entity.Question;
import com.exam.service.KimiAiService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Kimi AI服务实现类
 * 调用Kimi API智能生成题目
 */
@Slf4j
@Service
public class KimiAiServiceImpl implements KimiAiService {

    @Value("${kimi.api.api-key:}")
    private String kimiApiKey; // Kimi API密钥

    @Value("${kimi.api.base-url:https://api.moonshot.cn/v1}")
    private String kimiBaseUrl; // Kimi API基础地址

    @Value("${kimi.api.model:moonshot-v1-8k}")
    private String kimiModel; // 使用的模型

    private final WebClient webClient;

    public KimiAiServiceImpl() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public List<QuestionImportDto> generateQuestions(AiGenerateRequestDto request) {
        try {
            // 添加调试日志
            log.info("Kimi API配置检查:");
            log.info("kimiApiKey: {}", kimiApiKey != null && !kimiApiKey.isEmpty() ? "已配置" : "未配置");
            log.info("kimiBaseUrl: {}", kimiBaseUrl);
            log.info("kimiModel: {}", kimiModel);

            // 构建提示词
            String prompt = buildPrompt(request);
            // 调用Kimi API
            String response = callKimiApi(prompt);
            // 解析响应并转换为题目列表
            return parseResponse(response, request);

        } catch (Exception e) {
            log.error("调用Kimi API生成题目失败", e);
            throw new RuntimeException("AI生成题目失败: " + e.getMessage());
        }
    }

    @Override
    public List<Long> createPaperWithKimi(AiPaperDto aiPaperDto, RuleDto ruleDto, Integer index, List<Question> candidate) {

        // 2.2 构建针对该规则的 prompt（把候选题以表格/结构化方式列出，强制 JSON 返回）
        String prompt = buildPromptForRule(aiPaperDto, ruleDto, index, candidate);

        // 2.3 调用 Kimi 获取 JSON 返回（含重试 & 容错）
        String aiContent = callKimi2CreatePaper(prompt);

        // 2.4 解析 JSON，得到 ID 列表；若 AI 返回数量不足或包含 DB 外 ID，则回退/过滤并补足
        List<Long> selectedForRule = parseIdsPaper(aiContent, index);

        return selectedForRule;
    }

    /**
     * 构建发送给AI的提示词
     */
    private String buildPrompt(AiGenerateRequestDto request) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("请为我生成").append(request.getCount()).append("道关于【")
                .append(request.getTopic()).append("】的题目。\n\n");

        prompt.append("要求：\n");

        // 题目类型要求
        if (request.getTypes() != null && !request.getTypes().isEmpty()) {
            List<String> typeList = Arrays.asList(request.getTypes().split(","));
            prompt.append("- 题目类型：");
            for (String type : typeList) {
                switch (type.trim()) {
                    case "CHOICE":
                        prompt.append("选择题");
                        if (request.getIncludeMultiple() != null && request.getIncludeMultiple()) {
                            prompt.append("(包含单选和多选)");
                        }
                        prompt.append(" ");
                        break;
                    case "JUDGE":
                        prompt.append("判断题（**重要：确保正确答案和错误答案的数量大致平衡，不要全部都是正确或错误**） ");
                        break;
                    case "TEXT":
                        prompt.append("简答题 ");
                        break;
                }
            }
            prompt.append("\n");
        }

        // 难度要求
        if (request.getDifficulty() != null) {
            String difficultyText = switch (request.getDifficulty()) {
                case "EASY" -> "简单";
                case "MEDIUM" -> "中等";
                case "HARD" -> "困难";
                default -> "中等";
            };
            prompt.append("- 难度等级：").append(difficultyText).append("\n");
        }

        // 额外要求
        if (request.getRequirements() != null && !request.getRequirements().isEmpty()) {
            prompt.append("- 特殊要求：").append(request.getRequirements()).append("\n");
        }

        // 判断题特别要求
        if (request.getTypes() != null && request.getTypes().contains("JUDGE")) {
            prompt.append("- **判断题特别要求**：\n");
            prompt.append("  * 确保生成的判断题中，正确答案(TRUE)和错误答案(FALSE)的数量尽量平衡\n");
            prompt.append("  * 不要所有判断题都是正确的或都是错误的\n");
            prompt.append("  * 错误的陈述应该是常见的误解或容易混淆的概念\n");
            prompt.append("  * 正确的陈述应该是重要的基础知识点\n");
        }

        prompt.append("\n请严格按照以下JSON格式返回，不要包含任何其他文字：\n");
        prompt.append("```json\n");
        prompt.append("{\n");
        prompt.append("  \"questions\": [\n");
        prompt.append("    {\n");
        prompt.append("      \"title\": \"题目内容\",\n");
        prompt.append("      \"type\": \"CHOICE|JUDGE|TEXT\",\n");
        prompt.append("      \"multi\": true/false,\n");
        prompt.append("      \"difficulty\": \"EASY|MEDIUM|HARD\",\n");
        prompt.append("      \"score\": 5,\n");
        prompt.append("      \"choices\": [\n");
        prompt.append("        {\"content\": \"选项内容\", \"isCorrect\": true/false, \"sort\": 1}\n");
        prompt.append("      ],\n");
        prompt.append("      \"answer\": \"TRUE或FALSE(判断题专用)|文本答案(简答题专用)\",\n");
        prompt.append("      \"analysis\": \"题目解析\"\n");
        prompt.append("    }\n");
        prompt.append("  ]\n");
        prompt.append("}\n");
        prompt.append("```\n\n");

        prompt.append("注意：\n");
        prompt.append("1. 选择题必须有choices数组，判断题和简答题设置answer字段\n");
        prompt.append("2. 多选题的multi字段设为true，单选题设为false\n");
        prompt.append("3. **判断题的answer字段只能是\"TRUE\"或\"FALSE\"，请确保答案分布合理**\n");
        prompt.append("4. 每道题都要有详细的解析\n");
        prompt.append("5. 题目要有实际价值，贴近实际应用场景\n");
        prompt.append("6. 严格按照JSON格式返回，确保可以正确解析\n");

        // 如果只生成判断题，额外强调答案平衡
        if (request.getTypes() != null && request.getTypes().equals("JUDGE") && request.getCount() > 1) {
            prompt.append("7. **判断题答案分布要求**：在").append(request.getCount()).append("道判断题中，");
            int halfCount = request.getCount() / 2;
            if (request.getCount() % 2 == 0) {
                prompt.append("请生成").append(halfCount).append("道正确(TRUE)和").append(halfCount).append("道错误(FALSE)的题目");
            } else {
                prompt.append("请生成约").append(halfCount).append("-").append(halfCount + 1).append("道正确(TRUE)和约").append(halfCount).append("-").append(halfCount + 1).append("道错误(FALSE)的题目");
            }
        }

        return prompt.toString();
    }

    /**
     * 调用Kimi API
     */
    private String callKimiApi(String prompt) {
        if (kimiApiKey == null || kimiApiKey.isEmpty()) {
            throw new RuntimeException("未配置Kimi API密钥，请在配置文件中设置kimi.api.api-key");
        }

        log.info("开始调用Kimi API生成题目...");

        // 构建请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", kimiModel);
        requestBody.put("max_tokens", 4000);
        requestBody.put("temperature", 0.7);

        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);
        messages.add(message);
        requestBody.put("messages", messages);

        // 重试机制
        int maxRetries = 3;
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                log.info("第{}次尝试调用Kimi API", attempt);

                // 发送请求，增加超时时间到120秒
                Mono<String> responseMono = webClient.post()
                        .uri(kimiBaseUrl + "/chat/completions")
                        /*
                         * Authorization: Bearer [API_KEY] 的格式含义 这是行业通用的 Bearer Token 认证规范（属于 HTTP 标准认证方式之一），格式拆解： Authorization：HTTP 协议规定的 “认证头” 字段，告诉服务器 “这个请求需要验证身份” Bearer：表示 “令牌类型”（可以理解为
                         * “凭证类型”），说明后面跟着的是一个 “访问令牌” [API_KEY]：你在 Kimi 平台申请的专属密钥（类似你的 “API 密码”）
                         */
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + kimiApiKey)
                        .bodyValue(requestBody.toJSONString())
                        .retrieve()
                        .bodyToMono(String.class)
                        .timeout(Duration.ofSeconds(120)); // 增加到120秒超时

                String response = responseMono.block();
                log.info("Kimi API调用成功，响应长度: {}", response != null ? response.length() : 0);

                // 解析响应
                JSONObject responseJson = JSON.parseObject(response);
                if (responseJson.containsKey("error")) {
                    JSONObject error = responseJson.getJSONObject("error");
                    String errorMessage = error.getString("message");
                    log.error("Kimi API返回错误: {}", errorMessage);
                    // 如果是限流错误，等待后重试
                    if (errorMessage.contains("rate limit") || errorMessage.contains("too many requests")) {
                        if (attempt < maxRetries) {
                            log.info("遇到限流，等待{}秒后重试...", attempt * 5);
                            Thread.sleep(attempt * 5000); // 等待5秒、10秒、15秒
                            continue;
                        }
                    }
                    throw new RuntimeException("Kimi API错误: " + errorMessage);
                }

                JSONArray choices = responseJson.getJSONArray("choices");
                if (choices != null && !choices.isEmpty()) {
                    String content = choices.getJSONObject(0).getJSONObject("message").getString("content");
                    log.info("AI生成内容获取成功，内容长度: {}", content.length());
                    return content;
                } else {
                    throw new RuntimeException("Kimi API返回的响应格式不正确");
                }

            } catch (Exception e) {
                log.error("第{}次调用Kimi API失败: {}", attempt, e.getMessage());

                if (attempt == maxRetries) {
                    // 最后一次尝试失败，返回友好的错误信息
                    if (e.getMessage().contains("timeout")) {
                        throw new RuntimeException("AI服务响应超时，请稍后重试。如果问题持续存在，建议减少生成题目数量或简化需求描述。");
                    } else if (e.getMessage().contains("rate limit")) {
                        throw new RuntimeException("AI服务请求过于频繁，请稍后再试。");
                    } else if (e.getMessage().contains("unauthorized") || e.getMessage().contains("invalid")) {
                        throw new RuntimeException("AI服务认证失败，请检查API密钥配置。");
                    } else {
                        throw new RuntimeException("AI服务暂时不可用: " + e.getMessage() + "。请稍后重试或联系管理员。");
                    }
                }

                // 非最后一次尝试，等待后重试
                try {
                    Thread.sleep(2000); // 等待2秒后重试
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("AI生成被中断");
                }
            }
        }

        throw new RuntimeException("AI生成题目失败，已重试" + maxRetries + "次");
    }

    /**
     * 解析AI响应并转换为题目列表
     */
    private List<QuestionImportDto> parseResponse(String response, AiGenerateRequestDto request) {
        List<QuestionImportDto> questions = new ArrayList<>();

        try {
            // 提取JSON部分
            String jsonContent = extractJsonFromResponse(response);

            JSONObject jsonResponse = JSON.parseObject(jsonContent);
            JSONArray questionsArray = jsonResponse.getJSONArray("questions");

            for (int i = 0; i < questionsArray.size(); i++) {
                JSONObject questionJson = questionsArray.getJSONObject(i);
                QuestionImportDto question = new QuestionImportDto();

                question.setTitle(questionJson.getString("title"));
                question.setType(questionJson.getString("type"));
                question.setMulti(questionJson.getBoolean("multi"));
                question.setDifficulty(questionJson.getString("difficulty"));
                question.setScore(questionJson.getInteger("score"));
                question.setAnalysis(questionJson.getString("analysis"));
                question.setCategoryId(request.getCategoryId());

                // 处理选择题选项
                if ("CHOICE".equals(question.getType()) && questionJson.containsKey("choices")) {
                    JSONArray choicesArray = questionJson.getJSONArray("choices");
                    List<QuestionImportDto.ChoiceImportDto> choices = new ArrayList<>();

                    for (int j = 0; j < choicesArray.size(); j++) {
                        JSONObject choiceJson = choicesArray.getJSONObject(j);
                        QuestionImportDto.ChoiceImportDto choice = new QuestionImportDto.ChoiceImportDto();
                        choice.setContent(choiceJson.getString("content"));
                        choice.setIsCorrect(choiceJson.getBoolean("isCorrect"));
                        choice.setSort(choiceJson.getInteger("sort"));
                        choices.add(choice);
                    }
                    question.setChoices(choices);
                } else {
                    // 判断题和简答题
                    String rawAnswer = questionJson.getString("answer");

                    // 对判断题答案进行标准化处理
                    if ("JUDGE".equals(question.getType()) && rawAnswer != null) {
                        // 将中文答案转换为英文标准答案
                        if ("正确".equals(rawAnswer) || "对".equals(rawAnswer) || "TRUE".equalsIgnoreCase(rawAnswer)) {
                            rawAnswer = "TRUE";
                        } else if ("错误".equals(rawAnswer) || "错".equals(rawAnswer) || "FALSE".equalsIgnoreCase(rawAnswer)) {
                            rawAnswer = "FALSE";
                        }
                        // 如果都不匹配，记录日志但不抛异常，让验证逻辑处理
                        if (!"TRUE".equals(rawAnswer) && !"FALSE".equals(rawAnswer)) {
                            log.warn("AI生成的判断题答案格式不标准: {}, 题目: {}", rawAnswer, question.getTitle());
                        }
                    }

                    question.setAnswer(rawAnswer);
                }

                questions.add(question);
            }

        } catch (Exception e) {
            log.error("解析AI响应失败", e);
            throw new RuntimeException("解析AI生成的题目失败: " + e.getMessage());
        }

        return questions;
    }

    /**
     * 从响应中提取JSON内容
     */
    private String extractJsonFromResponse(String response) {
        // 查找JSON代码块
        int startIndex = response.indexOf("```json");
        int endIndex = response.lastIndexOf("```");

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return response.substring(startIndex + 7, endIndex).trim();
        }

        // 如果没有代码块标记，尝试查找JSON对象
        startIndex = response.indexOf("{");
        endIndex = response.lastIndexOf("}");

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return response.substring(startIndex, endIndex + 1).trim();
        }

        throw new RuntimeException("无法从AI响应中提取JSON内容");
    }

    private String callKimi2CreatePaper(String prompt) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", kimiModel);
        requestBody.put("max_tokens", "2000");
        requestBody.put("temperature", 0.7);

        JSONArray messages = new JSONArray();
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);
        messages.add(message);
        requestBody.put("messages", messages);

        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                log.info("调用 Kimi API，第 {} 次尝试", attempt);
                String resp = webClient.post()
                        .uri(kimiBaseUrl + "/chat/completions")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + kimiApiKey)
                        .bodyValue(requestBody.toJSONString())
                        .retrieve()
                        .bodyToMono(String.class)
                        .timeout(Duration.ofMillis(3000))
                        .block();

                if (resp == null) {
                    throw new RuntimeException("Kimi返回空响应");
                }
                JSONObject respJson = JSON.parseObject(resp);
                if (respJson.containsKey("error")) {
                    String err = respJson.getJSONObject("error").getString("message");
                    log.error("Kimi 返回错误: {}", err);
                    if ((err.contains("rate limit") || err.contains("too many requests")) && attempt < 3) {
                        Thread.sleep(attempt * 5000L);
                        continue;
                    }
                    throw new RuntimeException("Kimi API错误: " + err);
                }
                JSONArray choices = respJson.getJSONArray("choices");
                if (choices != null && !choices.isEmpty()) {
                    String content = choices.getJSONObject(0).getJSONObject("message").getString("content");
                    log.info("Kimi 返回内容长度: {}", content.length());
                    return content;
                } else {
                    throw new RuntimeException("Kimi返回格式异常");
                }
            } catch (Exception e) {
                log.error("Kimi调用第{}次失败: {}", attempt, e.getMessage());
                if (attempt == 3) {
                    if (e.getMessage() != null && e.getMessage().contains("timeout")) {
                        throw new RuntimeException("AI服务响应超时，请稍后重试或降低请求复杂度");
                    }
                    throw new RuntimeException("AI服务不可用: " + e.getMessage());
                }
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new RuntimeException("Kimi API调用失败（重试耗尽）");
    }

    private List<Long> parseIdsPaper(String aiContent, int expectedRuleIndex) {
        if (StringUtils.isBlank(aiContent)) {
            return Collections.emptyList();
        }

        // AI 可能会在返回前后带上文本，尝试提取第一个 JSON 对象
        String jsonText = extractJsonObject(aiContent);
        if (jsonText == null) {
            log.error("无法从 AI 返回中提取 JSON：{}", aiContent);
            return Collections.emptyList();
        }

        try {
            JSONObject obj = JSON.parseObject(jsonText);
            // 如果包含 ruleIndex 校验，保证与当前规则一致（非强制）
            if (obj.containsKey("ruleIndex")) {
                Integer ri = obj.getInteger("ruleIndex");
                if (ri != null && ri != expectedRuleIndex) {
                    log.warn("AI 返回的 ruleIndex ({}) 与期望 ({}) 不一致", ri, expectedRuleIndex);
                }
            }
            JSONArray arr = obj.getJSONArray("questionIds");
            if (arr == null)
                return Collections.emptyList();
            return arr.stream().map(o -> Long.valueOf(o.toString())).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("解析 AI 返回 JSON 失败: {}, 原文: {}", e.getMessage(), aiContent);
            return Collections.emptyList();
        }
    }

    // 提取第一个 JSON 对象字符串（从第一个 '{' 到其匹配的 '}'）
    private String extractJsonObject(String text) {
        int start = text.indexOf('{');
        if (start < 0)
            return null;
        int brace = 0;
        for (int i = start; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '{')
                brace++;
            else if (c == '}') {
                brace--;
                if (brace == 0) {
                    return text.substring(start, i + 1);
                }
            }
        }
        return null;
    }

    // 简单确定每题分值（根据规则匹配优先使用规则分值）
    private int determineScoreForQuestion(Question q, List<RuleDto> rules) {
        for (RuleDto r : rules) {
            if (r.getType() != null && r.getType().name().equals(q.getType())) {
                return r.getScore() == null ? 0 : r.getScore();
            }
        }
        return q.getScore() == null ? 0 : q.getScore();
    }

    private String buildPromptForRule(AiPaperDto aiPaperDto, RuleDto rule, int ruleIndex, List<Question> candidates) {
        StringBuilder sb = new StringBuilder();
        sb.append("你是专业的组卷助手。任务：从下面的候选题中，按规则挑选出题目ID列表。\n");
        sb.append("严格要求：输出仅为合法 JSON，结构为 {\"ruleIndex\": <number>, \"questionIds\": [id1,id2,...]}\n");
        sb.append("不能包含任何多余文字或解释，ID 必须来自下面候选清单。\n\n");

        sb.append(String.format("试卷名称：%s\n描述：%s\n规则索引：%d\n", aiPaperDto.getName(),
                Optional.ofNullable(aiPaperDto.getDescription()).orElse("无描述"), ruleIndex));
        sb.append(String.format("规则：题型=%s, 数量=%d, 难度=%s, 分类=%s, 每题分=%d\n\n",
                rule.getType(), rule.getCount(), rule.getDifficulty(), rule.getCategoryIds(), rule.getScore()));

        sb.append("候选题列表（仅列出必要字段）：\n");
        for (Question q : candidates) {
            sb.append(String.format("ID:%d | type:%s | multi:%s | category:%s | difficulty:%s | score:%s\n",
                    q.getId(), q.getType(), q.getMulti(), q.getCategoryId(), q.getDifficulty(), q.getScore()));
        }

        sb.append("\n返回示例：\n");
        sb.append("{\"ruleIndex\": ").append(ruleIndex).append(", \"questionIds\": [1001,1002,1003]}\n");
        sb.append("请返回 JSON，确保 questionIds 数量尽量满足规则数量（如果无法满足，请返回全部满足条件的ID）。");
        return sb.toString();
    }

}