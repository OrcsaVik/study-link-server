/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 题目选项实体类 - 选择题的选项信息
 */
@Data
@TableName("question_choices")
@Schema(description = "题目选项信息")
public class QuestionChoice {

    @Schema(description = "选项ID，唯一标识", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id; // 选项ID

    @Schema(description = "关联的题目ID", example = "1")
    private Long questionId; // 题目ID

    @Schema(description = "选项内容", example = "Java是面向对象编程语言")
    private String content; // 选项内容

    @Schema(description = "是否为正确答案", example = "true")
    private Boolean isCorrect; // 是否正确答案

    @Schema(description = "选项排序序号", example = "1")
    private Integer sort; // 排序
}