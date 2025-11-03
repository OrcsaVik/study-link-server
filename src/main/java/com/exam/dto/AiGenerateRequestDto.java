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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

/**
 * AI生成题目请求DTO - 智能生成题目所需的参数
 */
@Data
@Schema(description = "AI智能生成题目请求参数")
public class AiGenerateRequestDto {

    @Schema(description = "生成题目的主题", example = "Java面向对象编程", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "主题不能为空")
    private String topic; // 生成题目的主题，如"Spring框架"

    @Schema(description = "生成题目的数量，范围1-20", example = "5", minimum = "1", maximum = "20", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 1, message = "题目数量至少为1")
    @Max(value = 20, message = "题目数量最多为20")
    private Integer count; // 生成题目数量

    @Schema(description = "题目类型，多个用逗号分隔", example = "CHOICE,JUDGE,TEXT", allowableValues = {"CHOICE", "JUDGE", "TEXT", "CHOICE,JUDGE", "CHOICE,TEXT", "JUDGE,TEXT", "CHOICE,JUDGE,TEXT"})
    private String types; // 题目类型：如"CHOICE,JUDGE,TEXT" 多个用逗号分隔

    @Schema(description = "题目难度等级", example = "MEDIUM", allowableValues = {"EASY", "MEDIUM", "HARD"})
    private String difficulty; // 难度：EASY、MEDIUM、HARD

    @Schema(description = "题目分类ID，可以指定生成题目所属的分类", example = "1")
    private Long categoryId; // 分类ID

    @Schema(description = "是否包含多选题，仅在题目类型包含CHOICE时有效", example = "false")
    private Boolean includeMultiple; // 是否包含多选题

    @Schema(description = "额外的生成要求和说明", example = "重点考察实际应用，包含代码示例")
    private String requirements; // 额外要求，如"重点考察实际应用"
}