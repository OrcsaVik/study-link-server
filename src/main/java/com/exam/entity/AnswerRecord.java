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
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 答题记录表 - 存储学生每道题的答题详情
 */
@TableName(value = "answer_record")
@Data
@NoArgsConstructor
@Schema(description = "答题记录信息")
public class AnswerRecord implements Serializable {

    @Schema(description = "答题记录ID，唯一标识", example = "1")
    @TableId(type = IdType.AUTO)
    private Integer id; // 主键ID

    @Schema(description = "关联的考试记录ID", example = "1")
    private Integer examRecordId; // 考试记录ID

    @Schema(description = "题目ID", example = "5")
    private Integer questionId; // 题目ID

    @Schema(description = "学生提交的答案", example = "A")
    private String userAnswer; // 用户答案

    @Schema(description = "该题得分", example = "5")
    private Integer score; // 得分

    @Schema(description = "答题正确性", example = "1", allowableValues = {"0", "1", "2"})
    private Integer isCorrect; // 是否正确 (0: 错误, 1: 正确, 2: 部分正确)

    @Schema(description = "AI智能批改的评价意见", example = "答案基本正确，但缺少关键概念的解释...")
    private String aiCorrection; // AI批改意见

    @TableField(exist = false)
    private static final long serialVersionUID = 1L; // 序列化版本UID

    public AnswerRecord(Integer examRecordId, Integer questionId, String userAnswer) {
        this.examRecordId = examRecordId;
        this.questionId = questionId;
        this.userAnswer = userAnswer;
    }
}