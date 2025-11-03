/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

/**
 * 题目批量导入DTO - 用于Excel导入和AI生成题目的数据传输
 */
@Data
@Schema(description = "题目导入数据传输对象")
public class QuestionImportDto {

    @Schema(description = "题目标题内容", example = "以下关于Java面向对象编程的说法正确的是？", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title; // 题目标题

    @Schema(description = "题目类型", example = "CHOICE", allowableValues = {"CHOICE", "JUDGE", "TEXT"})
    private String type; // 题目类型：CHOICE、JUDGE、TEXT

    @Schema(description = "是否为多选题（仅选择题有效）", example = "false")
    private Boolean multi; // 是否为多选题（仅选择题有效）

    @Schema(description = "题目分类ID", example = "1")
    private Long categoryId; // 分类ID

    @Schema(description = "分类名称（仅用于显示，不会保存到数据库）", example = "Java基础")
    private String categoryName; // 分类名称（用于显示，不入库）

    @Schema(description = "题目难度级别", example = "MEDIUM", allowableValues = {"EASY", "MEDIUM", "HARD"})
    private String difficulty; // 难度：EASY、MEDIUM、HARD

    @Schema(description = "题目默认分值", example = "5")
    private Integer score; // 默认分值

    @Schema(description = "题目解析说明", example = "Java是面向对象编程语言，支持封装、继承、多态三大特性...")
    private String analysis; // 题目解析

    @Schema(description = "选择题选项列表（仅选择题需要）")
    private List<ChoiceImportDto> choices; // 选择题选项

    @Schema(description = "题目答案（判断题和简答题使用）", example = "正确")
    private String answer; // 答案（判断题和简答题）

    @Schema(description = "答题关键词（用于简答题AI评分）", example = "面向对象,封装,继承,多态")
    private String keywords; // 关键词（用于简答题评分）

    /**
     * 选择题选项导入DTO
     */
    @Data
    @Schema(description = "选择题选项数据")
    public static class ChoiceImportDto {

        @Schema(description = "选项内容", example = "Java支持多重继承", requiredMode = Schema.RequiredMode.REQUIRED)
        private String content; // 选项内容

        @Schema(description = "是否为正确答案", example = "false")
        private Boolean isCorrect; // 是否正确答案

        @Schema(description = "选项排序序号", example = "1")
        private Integer sort; // 排序
    }
}