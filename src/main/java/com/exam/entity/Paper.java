/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 试卷表 - 存储考试试卷的基本信息
 */
@TableName(value = "paper")
@Data
@Schema(description = "试卷信息")
public class Paper implements Serializable {

    @Schema(description = "试卷ID，唯一标识", example = "1")
    @TableId(type = IdType.AUTO)
    private Integer id; // 主键ID

    @Schema(description = "试卷名称", example = "Java基础知识测试")
    private String name; // 试卷名称

    @Schema(description = "试卷描述", example = "本试卷主要考察Java基础语法、面向对象编程等知识点")
    private String description; // 试卷描述

    @Schema(description = "试卷状态", example = "PUBLISHED", allowableValues = {"DRAFT", "PUBLISHED", "STOPPED"})
    private String status;

    @Schema(description = "试卷总分", example = "100.0")
    private BigDecimal totalScore; // 总分

    @Schema(description = "题目数量", example = "20")
    private Integer questionCount; // 题目数量

    @Schema(description = "考试时长，单位：分钟", example = "120")
    private Integer duration; // 考试时长

    @Schema(description = "试卷创建时间", example = "2024-01-15 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime; // 创建时间

    @Schema(description = "试卷更新时间", example = "2024-01-15 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("update_time")
    private LocalDateTime updateTime; // 更新时间

    @Schema(description = "试卷包含的题目列表，含题目详细信息和分值")
    @TableField(exist = false)
    private List<Question> questions; // 题目列表，非数据库字段

    @TableField(exist = false)
    private static final long serialVersionUID = 1L; // 序列化版本UID
}