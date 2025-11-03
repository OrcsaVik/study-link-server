/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 轮播图实体类 - 首页轮播图展示信息
 */
@Data
@TableName("banners")
@Schema(description = "轮播图信息")
public class Banner {

    @Schema(description = "轮播图ID，唯一标识", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id; // 轮播图ID

    @Schema(description = "轮播图标题", example = "智能考试系统介绍")
    private String title; // 轮播图标题

    @Schema(description = "轮播图描述内容", example = "基于AI技术的智能考试平台，支持在线考试、智能组卷等功能")
    private String description; // 轮播图描述

    @Schema(description = "轮播图片URL地址", example = "https://example.com/images/banner1.jpg")
    private String imageUrl; // 图片URL

    @Schema(description = "点击跳转链接，可选", example = "https://example.com/about")
    private String linkUrl; // 跳转链接

    @Schema(description = "排序顺序，数字越小越靠前", example = "1")
    private Integer sortOrder; // 排序顺序

    @Schema(description = "是否启用显示", example = "true")
    private Boolean isActive; // 是否启用

    @Schema(description = "轮播图创建时间", example = "2024-01-15 10:00:00")
    private LocalDateTime createTime; // 创建时间

    @Schema(description = "轮播图更新时间", example = "2024-01-15 10:00:00")
    private LocalDateTime updateTime; // 更新时间
}