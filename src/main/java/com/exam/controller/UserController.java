/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.controller;

import com.exam.common.Result;
import com.exam.dto.LoginRequest;
import com.exam.dto.LoginResponse;
import com.exam.entity.User;
import com.exam.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 用户控制器 - 处理用户认证和权限管理相关的HTTP请求
 * 包括用户登录、权限验证等功能
 */
@RestController // REST控制器，返回JSON数据
@RequestMapping("/api/user") // 用户API路径前缀
@CrossOrigin(origins = "*") // 允许跨域访问
@Tag(name = "用户管理", description = "用户相关操作，包括登录认证、权限验证等功能") // Swagger API分组
public class UserController {

    /**
     * 注入用户业务服务
     */
    @Autowired
    private UserService userService; // 注入用户服务

    /**
     * 用户登录
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    @PostMapping("/login") // 处理POST请求
    @Operation(summary = "用户登录", description = "用户通过用户名和密码进行登录验证，返回用户信息和token") // API描述
    public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 验证参数
        if (loginRequest.getUsername() == null || loginRequest.getUsername().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return Result.error("密码不能为空");
        }

        // 执行登录
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            return Result.error("用户名或密码错误");
        }

        // 构建登录响应
        LoginResponse response = new LoginResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setRealName(user.getRealName());
        response.setRole(user.getRole());

        response.setToken(UUID.randomUUID().toString()); // 简单的token生成

        return Result.success(response);
    }

    /**
     * 检查用户权限
     * @param userId 用户ID
     * @return 权限检查结果
     */
    @GetMapping("/check-admin/{userId}") // 处理GET请求
    @Operation(summary = "检查管理员权限", description = "验证指定用户是否具有管理员权限") // API描述
    public Result<Boolean> checkAdmin(
                                      @Parameter(description = "用户ID") @PathVariable Long userId) {
        boolean isAdmin = userService.isAdmin(userId);
        return Result.success(isAdmin);
    }
}