/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exam.dto.StatsDto;
import com.exam.entity.*;
import com.exam.mapper.*;
import com.exam.service.StatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 统计数据服务实现类
 */
@Slf4j
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private QuestionMapper questionMapper; // 题目Mapper

    @Autowired
    private UserMapper userMapper; // 用户Mapper

    @Autowired
    private ExamRecordMapper examRecordMapper; // 考试记录Mapper

    @Autowired
    private CategoryMapper categoryMapper; // 分类Mapper

    @Autowired
    private PaperMapper paperMapper; // 试卷Mapper

    @Override
    public StatsDto getSystemStats() {
        StatsDto stats = new StatsDto();

        try {
            // 统计题目总数 // 查询题目数量
            Long questionCount = questionMapper.selectCount(new QueryWrapper<>());
            stats.setQuestionCount(questionCount);
            log.info("题目总数: {}", questionCount);
        } catch (Exception e) {
            log.error("查询题目总数失败: {}", e.getMessage());
            stats.setQuestionCount(0L);
        }

        try {
            // 统计用户总数 // 查询用户数量
            Long userCount = userMapper.selectCount(new QueryWrapper<>());
            stats.setUserCount(userCount);
            log.info("用户总数: {}", userCount);
        } catch (Exception e) {
            log.error("查询用户总数失败: {}", e.getMessage());
            stats.setUserCount(0L);
        }

        try {
            // 统计考试总场次 // 查询考试记录总数
            Long examCount = examRecordMapper.selectCount(new QueryWrapper<>());
            stats.setExamCount(examCount);
            log.info("考试总场次: {}", examCount);
        } catch (Exception e) {
            log.error("查询考试总场次失败: {}", e.getMessage());
            stats.setExamCount(0L);
        }

        try {
            // 统计今日考试次数 // 查询今天的考试记录（使用create_time字段）
            LocalDate today = LocalDate.now();
            LocalDateTime startOfDay = today.atStartOfDay(); // 今天00:00:00
            LocalDateTime endOfDay = today.atTime(LocalTime.MAX); // 今天23:59:59

            QueryWrapper<ExamRecord> todayQueryWrapper = new QueryWrapper<>();
            todayQueryWrapper.between("create_time", startOfDay, endOfDay); // 使用正确的字段名
            Long todayExamCount = examRecordMapper.selectCount(todayQueryWrapper);
            stats.setTodayExamCount(todayExamCount);
            log.info("今日考试次数: {}", todayExamCount);
        } catch (Exception e) {
            log.error("查询今日考试次数失败: {}", e.getMessage());
            stats.setTodayExamCount(0L);
        }

        try {
            // 统计分类总数 // 查询分类数量
            Long categoryCount = categoryMapper.selectCount(new QueryWrapper<>());
            stats.setCategoryCount(categoryCount);
            log.info("分类总数: {}", categoryCount);
        } catch (Exception e) {
            log.error("查询分类总数失败: {}", e.getMessage());
            stats.setCategoryCount(0L);
        }

        try {
            // 统计试卷总数 // 查询试卷数量
            Long paperCount = paperMapper.selectCount(new QueryWrapper<>());
            stats.setPaperCount(paperCount);
            log.info("试卷总数: {}", paperCount);
        } catch (Exception e) {
            log.error("查询试卷总数失败: {}", e.getMessage());
            stats.setPaperCount(0L);
        }

        log.info("系统统计数据获取完成: {}", stats);
        return stats;
    }
}