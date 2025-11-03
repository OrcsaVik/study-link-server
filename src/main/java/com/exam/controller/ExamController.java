/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.controller;

import com.exam.common.Result;
import com.exam.dto.StartExamDto;
import com.exam.dto.SubmitAnswerDto;
import com.exam.entity.ExamRecord;
import com.exam.service.ExamService;
import com.exam.service.PaperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考试控制器 - 处理考试流程相关的HTTP请求
 * 包括开始考试、提交答案、AI批阅、成绩查询等功能
 */
@RestController // REST控制器，返回JSON数据
@RequestMapping("/api/exams") // 考试API路径前缀
@CrossOrigin(origins = "*") // 允许跨域访问
@Tag(name = "考试管理", description = "考试流程相关操作，包括开始考试、答题提交、AI批阅、成绩查询等功能") // Swagger API分组
public class ExamController {

    /**
     * 注入考试业务服务
     */
    @Autowired
    private ExamService examService; // 考试服务

    /**
     * 注入试卷业务服务
     */
    @Autowired
    private PaperService paperService; // 试卷服务

    /**
     * 开始考试 - 创建新的考试记录
     * @param startExamDto 开始考试请求DTO
     * @return 考试记录
     */
    @PostMapping("/start") // 处理POST请求
    @Operation(summary = "开始考试", description = "学生开始考试，创建考试记录并返回试卷内容") // API描述
    public Result<ExamRecord> startExam(@RequestBody StartExamDto startExamDto) {
        // TODO: 从SecurityContext获取当前登录用户ID // 暂时使用固定用户ID
        Integer userId = 1; // 假设用户ID为1
        ExamRecord examRecord = examService.startExam(startExamDto.getPaperId(), startExamDto.getStudentName(), userId);
        return Result.success(examRecord, "考试开始成功");
    }

    /**
     * 提交答案 - 学生提交考试答案
     * @param examRecordId 考试记录ID
     * @param answers      答案列表
     */
    @PostMapping("/{examRecordId}/submit") // 处理POST请求
    @Operation(summary = "提交考试答案", description = "学生提交考试答案，系统记录答题情况") // API描述
    public Result<Void> submitAnswers(
                                      @Parameter(description = "考试记录ID") @PathVariable Integer examRecordId,
                                      @RequestBody List<SubmitAnswerDto> answers) {
        examService.submitAnswers(examRecordId, answers);
        return Result.success("答案提交成功");
    }

    /**
     * AI自动批阅 - 触发试卷智能批阅
     * @param examRecordId 考试记录ID
     */
    @PostMapping("/{examRecordId}/grade") // 处理POST请求
    @Operation(summary = "AI自动批阅", description = "使用AI技术自动批阅试卷，特别是简答题的智能评分") // API描述
    public Result<ExamRecord> gradeExam(
                                        @Parameter(description = "考试记录ID") @PathVariable Integer examRecordId) {
        ExamRecord examRecord = examService.gradeExam(examRecordId);
        return Result.success(examRecord, "试卷批阅完成");
    }

    /**
     * 根据ID获取考试记录详情 - 查询具体考试结果
     */
    @GetMapping("/{id}") // 处理GET请求
    @Operation(summary = "查询考试记录详情", description = "获取指定考试记录的详细信息，包括答题情况和得分") // API描述
    public Result<ExamRecord> getExamRecordById(
                                                @Parameter(description = "考试记录ID") @PathVariable Integer id) {
        ExamRecord record = examService.getExamRecordDetail(id);
        return Result.success(record);
    }

    /**
     * 获取考试记录列表 - 查询所有考试记录
     */
    @GetMapping("/records") // 处理GET请求
    @Operation(summary = "获取考试记录列表", description = "获取所有考试记录列表，包含基本信息和成绩") // API描述
    public Result<List<ExamRecord>> getMyRecords() {
        // 由于没有用户登录系统，返回所有考试记录 // 返回所有考试记录
        List<ExamRecord> records = examService.list();
        // 为每个记录加载试卷信息 // 补充试卷详细信息
        records.forEach(record -> {
            record.setPaper(paperService.getPaperWithQuestions(record.getExamId()));
        });
        return Result.success(records);
    }
}