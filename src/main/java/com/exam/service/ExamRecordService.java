/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exam.entity.ExamRecord;
import com.exam.vo.ExamRankingVO;

import java.util.List;

/**
 * 考试记录Service接口
 * 定义考试记录相关的业务方法
 */
public interface ExamRecordService extends IService<ExamRecord> {

    /**
     * 根据试卷ID获取考试记录
     * @param examId 试卷ID
     * @return 考试记录列表
     */
    List<ExamRecord> getRecordsByExamId(Long examId);

    /**
     * 根据考生姓名获取考试记录
     * @param studentName 考生姓名
     * @return 考试记录列表
     */
    List<ExamRecord> getRecordsByStudentName(String studentName);

    /**
     * 保存考试记录
     * @param examRecord 考试记录
     * @return 是否保存成功
     */
    boolean saveExamRecord(ExamRecord examRecord);

    /**
     * 获取考试排行榜 - 优化版本
     * 使用SQL关联查询，一次性获取所有需要的数据，避免N+1查询问题
     * 
     * @param paperId 试卷ID，可选参数，不传则查询所有试卷
     * @param limit 显示数量限制，可选参数，不传则返回所有记录
     * @return 排行榜列表
     */
    List<ExamRankingVO> getExamRankingOptimized(Integer paperId, Integer limit);
}