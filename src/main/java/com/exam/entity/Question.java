/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * 题目实体类 - 考试题目信息模型
 * 
 * 这是系统的核心实体类之一，用于存储各种类型的考试题目
 * 
 * MyBatis Plus高级特性教学：
 * 1. @TableField(exist = false)：标记非数据库字段，用于关联查询结果
 * 2. FieldFill.INSERT/INSERT_UPDATE：自动填充创建和更新时间
 * 3. 实体类关联：通过@TableField(exist = false)实现多表关联
 * 
 * 业务设计：
 * - 支持多种题型：选择题(CHOICE)、判断题(JUDGE)、简答题(TEXT)
 * - 支持难度分级：简单(EASY)、中等(MEDIUM)、困难(HARD)
 * - 支持分类管理：通过categoryId关联分类表
 * 
 * @author 智能学习平台开发团队
 * @version 1.0
 */
@Data // Lombok注解：自动生成所有getter、setter方法
@TableName("questions") // 对应数据库表：questions
@Schema(description = "题目信息")
public class Question {

    @Schema(description = "题目ID，唯一标识", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "题目标题内容", example = "以下关于Java面向对象编程的说法正确的是？")
    private String title;

    @Schema(description = "题目类型", example = "CHOICE", allowableValues = {"CHOICE", "JUDGE", "TEXT"})
    private String type;

    @Schema(description = "是否为多选题，仅选择题有效", example = "false")
    private Boolean multi;

    @Schema(description = "题目分类ID", example = "1")
    private Long categoryId;

    @Schema(description = "题目难度等级", example = "MEDIUM", allowableValues = {"EASY", "MEDIUM", "HARD"})
    private String difficulty;

    @Schema(description = "题目默认分值", example = "5")
    private Integer score;

    @Schema(description = "在特定试卷中的分值", example = "10.0")
    @TableField(exist = false) // 标记为非数据库字段
    private BigDecimal paperScore;

    @Schema(description = "题目解析，详细的答案说明", example = "Java是面向对象编程语言，支持封装、继承、多态三大特性...")
    private String analysis;

    @Schema(description = "题目创建时间", example = "2024-01-15 10:30:00")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @Schema(description = "题目更新时间", example = "2024-01-15 10:30:00")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Schema(description = "选择题选项列表，包含A、B、C、D等选项")
    @TableField(exist = false)
    private List<QuestionChoice> choices;

    @Schema(description = "题目答案信息，包含正确答案和评分标准")
    @TableField(exist = false)
    private QuestionAnswer answer;

    @Schema(description = "题目所属分类信息")
    @TableField(exist = false)
    private Category category;
}