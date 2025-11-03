/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 考试排行榜VO - 排行榜展示专用数据对象
 * 只包含排行榜所需的核心字段，避免查询过多无用数据
 */
@Data
@Schema(description = "考试排行榜信息")
public class ExamRankingVO implements Serializable {

    @Schema(description = "考试记录ID", example = "1")
    private Integer id; // 考试记录ID

    @Schema(description = "考生姓名", example = "张三")
    private String studentName; // 考生姓名

    @Schema(description = "考试得分", example = "85")
    private Integer score; // 考试得分

    @Schema(description = "试卷ID", example = "1")
    private Integer examId; // 试卷ID

    @Schema(description = "试卷名称", example = "Java基础知识测试")
    private String paperName; // 试卷名称（通过关联查询获取）

    @Schema(description = "试卷总分", example = "100")
    private BigDecimal paperTotalScore; // 试卷总分（通过关联查询获取）

    @Schema(description = "考试开始时间", example = "2024-01-15 09:00:00")
    private LocalDateTime startTime; // 开始时间

    @Schema(description = "考试结束时间", example = "2024-01-15 11:00:00")
    private LocalDateTime endTime; // 结束时间

    @Schema(description = "考试用时（分钟）", example = "120")
    private Long duration; // 考试用时，单位分钟

    /**
     * 为了保持前端兼容性，提供paper对象格式的数据
     * 前端代码中使用 record.paper.name 和 record.paper.totalScore
     */
    @Schema(description = "试卷信息对象（兼容前端）")
    public Map<String, Object> getPaper() {
        Map<String, Object> paper = new HashMap<>();
        paper.put("id", this.examId);
        paper.put("name", this.paperName);
        paper.put("totalScore", this.paperTotalScore);
        return paper;
    }

    private static final long serialVersionUID = 1L; // 序列化版本号
}