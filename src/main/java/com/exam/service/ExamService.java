/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.dto.SubmitAnswerDto;
import com.exam.entity.ExamRecord;

import java.util.List;

/**
 * 考试服务接口
 */
public interface ExamService extends IService<ExamRecord> {

    /**
     * 开始一场考试
     * @param paperId 试卷ID
     * @param studentName 考生姓名
     * @param userId  用户ID
     * @return 考试记录
     */
    ExamRecord startExam(Integer paperId, String studentName, Integer userId);

    /**
     * 提交答案
     * @param examRecordId 考试记录ID
     * @param answers      用户答案列表
     */
    void submitAnswers(Integer examRecordId, List<SubmitAnswerDto> answers);

    /**
     * 批阅试卷（包含AI自动批阅）
     * @param examRecordId 考试记录ID
     * @return 批阅后的考试记录
     */
    ExamRecord gradeExam(Integer examRecordId);

    /**
     * 获取考试记录详情（包含试卷信息和答题记录）
     * @param examRecordId 考试记录ID
     * @return 考试记录详情
     */
    ExamRecord getExamRecordDetail(Integer examRecordId);
}
