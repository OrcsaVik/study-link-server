/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.entity.ExamRecord;
import com.exam.mapper.ExamRecordMapper;
import com.exam.service.ExamRecordService;
import com.exam.vo.ExamRankingVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考试记录Service实现类
 * 实现考试记录相关的业务逻辑
 */
@Service
public class ExamRecordServiceImpl extends ServiceImpl<ExamRecordMapper, ExamRecord> implements ExamRecordService {

    @Override
    public List<ExamRecord> getRecordsByExamId(Long examId) {
        // 根据试卷ID查询考试记录
        QueryWrapper<ExamRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("exam_id", examId)
                .orderByDesc("create_time");
        return this.list(queryWrapper);
    }

    @Override
    public List<ExamRecord> getRecordsByStudentName(String studentName) {
        // 根据考生姓名查询考试记录
        QueryWrapper<ExamRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_name", studentName);
        queryWrapper.orderByDesc("created_at");
        return this.list(queryWrapper);
    }

    @Override
    public boolean saveExamRecord(ExamRecord examRecord) {
        // 保存考试记录
        return this.save(examRecord);
    }

    @Override
    public List<ExamRankingVO> getExamRankingOptimized(Integer paperId, Integer limit) {
        // 使用优化的SQL关联查询获取排行榜数据
        // 一次查询解决N+1问题，大幅提升性能
        return baseMapper.selectExamRanking(paperId, limit);
    }
}