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
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体类 - 系统用户信息模型
 * 
 * MyBatis Plus教学要点：
 * 1. @TableName注解：指定对应的数据库表名
 * 2. @TableId注解：标识主键字段，AUTO表示数据库自增
 * 3. @TableField注解：处理字段映射，特别是驼峰命名与下划线的转换
 * 4. @Data注解：Lombok自动生成getter/setter、toString等方法
 * 
 * 数据库设计：
 * - 对应表：users
 * - 主键：id（自增）
 * - 索引：username（唯一索引）
 * 
 * @author 智能学习平台开发团队
 * @version 1.0
 */
@Data // Lombok注解：自动生成getter、setter、toString、equals、hashCode方法
@TableName("users") // MyBatis Plus注解：指定对应的数据库表名
@Schema(description = "用户信息")
public class User {

    @Schema(description = "用户ID，唯一标识", example = "1")
    @TableId(type = IdType.AUTO) // 主键注解，AUTO表示数据库自增
    private Long id;

    @Schema(description = "用户名，用于登录", example = "admin")
    private String username;

    @Schema(description = "用户密码", example = "******")
    private String password;

    @Schema(description = "用户真实姓名", example = "张三")
    @TableField("real_name") // 显式指定数据库字段名
    private String realName;

    @Schema(description = "用户角色", example = "ADMIN", allowableValues = {"ADMIN", "TEACHER", "STUDENT"})
    private String role;

    @Schema(description = "用户状态", example = "ACTIVE", allowableValues = {"ACTIVE", "INACTIVE"})
    private String status;

    @Schema(description = "用户创建时间", example = "2024-01-15 10:00:00")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "用户信息更新时间", example = "2024-01-15 10:00:00")
    @TableField("update_time")
    private LocalDateTime updateTime;
}