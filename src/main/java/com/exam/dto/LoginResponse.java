/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 登录响应DTO - 用户登录成功后返回的数据
 */
@Data
@Schema(description = "登录成功响应数据")
public class LoginResponse {

    @Schema(description = "用户ID", example = "1")
    private Long userId; // 用户ID

    @Schema(description = "用户名", example = "admin")
    private String username; // 用户名

    @Schema(description = "用户真实姓名", example = "管理员")
    private String realName; // 真实姓名

    @Schema(description = "用户角色", example = "ADMIN", allowableValues = {"ADMIN", "TEACHER", "STUDENT"})
    private String role; // 用户角色

    @Schema(description = "登录令牌，用于后续API调用的身份验证", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token; // 登录令牌
}