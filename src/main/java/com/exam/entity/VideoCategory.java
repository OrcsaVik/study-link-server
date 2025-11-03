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
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 视频分类实体类
 * 支持层级分类结构，用于组织视频内容
 */
@Data
@TableName("video_categories")
@Schema(description = "视频分类信息")
public class VideoCategory {

    @Schema(description = "分类ID，唯一标识", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "分类名称", example = "Java基础")
    private String name;

    @Schema(description = "分类描述", example = "Java编程语言基础知识讲解")
    private String description;

    @Schema(description = "父级分类ID，0为顶级分类", example = "0")
    private Long parentId;

    @Schema(description = "排序权重，数字越小越靠前", example = "1")
    private Integer sortOrder;

    @Schema(description = "状态：1-启用，0-禁用", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    // ========== 非数据库字段 ==========

    @Schema(description = "子分类列表，用于构建分类树结构")
    @TableField(exist = false)
    private List<VideoCategory> children;

    @Schema(description = "该分类下的视频数量", example = "15")
    @TableField(exist = false)
    private Long videoCount;

    @Schema(description = "父级分类名称", example = "编程技术")
    @TableField(exist = false)
    private String parentName;
}