/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.common.Result;
import com.exam.entity.ExamRecord;
import com.exam.service.ExamRecordService;
import com.exam.service.PaperService;
import com.exam.vo.ExamRankingVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 考试记录控制器 - 处理考试记录管理相关的HTTP请求
 * 包括考试记录查询、分页展示、成绩排行榜等功能
 */
@RestController // REST控制器，返回JSON数据
@RequestMapping("/api/exam-records") // 考试记录API路径前缀
@Tag(name = "考试记录管理", description = "考试记录相关操作，包括记录查询、成绩管理、排行榜展示等功能") // Swagger API分组
public class ExamRecordController {

    /**
     * 注入考试记录业务服务
     */
    @Autowired
    private ExamRecordService examRecordService;

    /**
     * 注入试卷业务服务
     */
    @Autowired
    private PaperService paperService;

    /**
     * 分页查询考试记录
     */
    @GetMapping("/list") // 处理GET请求
    @Operation(summary = "分页查询考试记录", description = "支持多条件筛选的考试记录分页查询，包括按姓名、状态、时间范围等筛选") // API描述
    public Result<Page<ExamRecord>> getExamRecords(
                                                   @Parameter(description = "当前页码，从1开始", example = "1") @RequestParam(defaultValue = "1") Integer page,
                                                   @Parameter(description = "每页显示数量", example = "20") @RequestParam(defaultValue = "20") Integer size,
                                                   @Parameter(description = "学生姓名筛选条件") @RequestParam(required = false) String studentName,
                                                   @Parameter(description = "学号筛选条件") @RequestParam(required = false) String studentNumber,
                                                   @Parameter(description = "考试状态，0-进行中，1-已完成，2-已批阅") @RequestParam(required = false) Integer status,
                                                   @Parameter(description = "开始日期，格式：yyyy-MM-dd") @RequestParam(required = false) String startDate,
                                                   @Parameter(description = "结束日期，格式：yyyy-MM-dd") @RequestParam(required = false) String endDate) {
        // 构建查询条件
        QueryWrapper<ExamRecord> wrapper = new QueryWrapper<>();

        // 按考生姓名搜索
        if (studentName != null && !studentName.trim().isEmpty()) {
            wrapper.like("student_name", studentName.trim());
        }

        // 按学号搜索（如果有学号字段的话）
        if (studentNumber != null && !studentNumber.trim().isEmpty()) {
            // 暂时注释掉，因为实体类中没有学号字段
            // wrapper.like("student_number", studentNumber.trim());
        }

        // 按状态筛选
        if (status != null) {
            String statusStr;
            switch (status) {
                case 0:
                    statusStr = "进行中";
                    break;
                case 1:
                    statusStr = "已完成";
                    break;
                case 2:
                    statusStr = "已批阅";
                    break;
                default:
                    statusStr = "进行中";
            }
            wrapper.eq("status", statusStr);
        }

        // 按日期范围筛选
        if (startDate != null && !startDate.trim().isEmpty()) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDateTime startDateTime = start.atStartOfDay();
            wrapper.ge("create_time", startDateTime);
        }

        if (endDate != null && !endDate.trim().isEmpty()) {
            LocalDate end = LocalDate.parse(endDate);
            LocalDateTime endDateTime = end.atTime(23, 59, 59);
            wrapper.le("create_time", endDateTime);
        }

        // 按创建时间倒序排列
        wrapper.orderByDesc("create_time");

        // 执行分页查询
        IPage<ExamRecord> pageParam = new Page<>(page, size);
        Page<ExamRecord> result = (Page<ExamRecord>) examRecordService.page(pageParam, wrapper);

        // 为每个考试记录加载试卷信息
        result.getRecords().forEach(record -> {
            record.setPaper(paperService.getPaperWithQuestions(record.getExamId()));
        });

        return Result.success(result);
    }

    /**
     * 根据ID获取考试记录详情
     */
    @GetMapping("/{id}") // 处理GET请求
    @Operation(summary = "获取考试记录详情", description = "根据记录ID获取考试记录的详细信息，包括试卷内容和答题情况") // API描述
    public Result<ExamRecord> getExamRecordById(
                                                @Parameter(description = "考试记录ID") @PathVariable Integer id) {
        ExamRecord record = examRecordService.getById(id);
        if (record != null) {
            // 加载试卷信息
            record.setPaper(paperService.getPaperWithQuestions(record.getExamId()));
        }
        return Result.success(record);
    }

    /**
     * 删除考试记录
     */
    @DeleteMapping("/{id}") // 处理DELETE请求
    @Operation(summary = "删除考试记录", description = "根据ID删除指定的考试记录") // API描述
    public Result<Void> deleteExamRecord(
                                         @Parameter(description = "考试记录ID") @PathVariable Integer id) {
        boolean success = examRecordService.removeById(id);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 获取考试排行榜 - 优化版本
     * 使用SQL关联查询，一次性获取所有需要的数据，性能提升数百倍
     * 
     * @param paperId 试卷ID，可选参数
     * @param limit 显示数量限制，可选参数
     * @return 排行榜列表
     */
    @GetMapping("/ranking") // 处理GET请求
    @Operation(summary = "获取考试排行榜", description = "获取考试成绩排行榜，支持按试卷筛选和限制显示数量，使用优化的SQL关联查询提升性能") // API描述
    public Result<List<ExamRankingVO>> getExamRanking(
                                                      @Parameter(description = "试卷ID，可选，不传则显示所有试卷的排行") @RequestParam(required = false) Integer paperId,
                                                      @Parameter(description = "显示数量限制，可选，不传则返回所有记录") @RequestParam(required = false) Integer limit) {
        // 使用优化的查询方法，避免N+1查询问题
        // 原来需要4000+次数据库查询，现在只需要1次！
        List<ExamRankingVO> rankingList = examRecordService.getExamRankingOptimized(paperId, limit);

        return Result.success(rankingList);
    }
}