/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.controller;

import com.exam.common.Result;
import com.exam.dto.StatsDto;
import com.exam.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 统计控制器 - 处理系统数据统计相关的HTTP请求
 * 包括系统概览数据、各种统计信息等功能
 */
@RestController // REST控制器，返回JSON数据
@RequestMapping("/api/stats") // 统计API路径前缀
@CrossOrigin(origins = "*") // 允许跨域访问
@Tag(name = "数据统计", description = "系统统计相关操作，包括概览数据、图表统计等功能") // Swagger API分组
public class StatsController {

    /**
     * 注入统计业务服务
     */
    @Autowired
    private StatsService statsService;

    /**
     * 获取系统统计数据
     * 包括题目数量、用户数量、考试数量等
     * @return 系统统计信息
     */
    @GetMapping("/overview") // 处理GET请求
    @Operation(summary = "获取系统概览统计", description = "获取系统的概览统计数据，包括题目、用户、考试等各项数量统计") // API描述
    public Result<StatsDto> getSystemStats() {
        StatsDto stats = statsService.getSystemStats();
        return Result.success(stats);
    }

    /**
     * 测试数据库连接
     * @return 测试结果
     */
    @GetMapping("/test") // 处理GET请求
    @Operation(summary = "测试数据库连接", description = "测试系统数据库连接状态，用于系统健康检查") // API描述
    public Result<String> testDatabase() {
        try {
            // 简单测试数据库连接，通过获取统计数据来验证
            StatsDto stats = statsService.getSystemStats();
            return Result.success("数据库连接正常，统计数据：" + stats.toString());
        } catch (Exception e) {
            return Result.error("数据库连接测试异常：" + e.getMessage());
        }
    }
}