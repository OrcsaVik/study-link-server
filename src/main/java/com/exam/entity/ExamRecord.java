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
import java.time.LocalDateTime;
import java.util.List;

/**
 * 考试记录表 - 存储学生的考试过程和结果数据
 */
@TableName(value = "exam_records")
@Data
@Schema(description = "考试记录信息")
public class ExamRecord implements Serializable {

    @Schema(description = "考试记录ID，唯一标识", example = "1")
    @TableId(type = IdType.AUTO)
    private Integer id; // 主键ID

    @Schema(description = "试卷ID，关联的考试试卷", example = "1")
    private Integer examId; // 试卷ID

    @Schema(description = "考生姓名", example = "张三")
    private String studentName; // 考生姓名

    @Schema(description = "考试得分", example = "85")
    private Integer score; // 得分

    @Schema(description = "答题记录，JSON格式存储所有答题内容", example = "[{\"questionId\":1,\"userAnswer\":\"A\"},{\"questionId\":2,\"userAnswer\":\"B\"}]")
    private String answers; // 答题记录

    @Schema(description = "考试开始时间", example = "2024-01-15 09:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime; // 开始时间

    @Schema(description = "考试结束时间", example = "2024-01-15 11:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime; // 结束时间

    @Schema(description = "考试状态", example = "已批阅", allowableValues = {"进行中", "已完成", "已批阅"})
    private String status; // 考试状态

    @Schema(description = "窗口切换次数，用于监控考试过程中的异常行为", example = "2")
    private Integer windowSwitches; // 窗口切换次数

    @Schema(description = "记录创建时间", example = "2024-01-15 09:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime; // 创建时间

    @Schema(description = "详细的答题记录列表，包含每题的答案和得分情况")
    @TableField(exist = false)
    private List<AnswerRecord> answerRecords; // 答案记录列表

    @Schema(description = "关联的试卷信息，包含试卷详细内容和题目")
    @TableField(exist = false)
    private Paper paper; // 试卷信息

    @TableField(exist = false)
    private static final long serialVersionUID = 1L; // 序列化版本UID
}