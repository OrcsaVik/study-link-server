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
 * 公告实体类 - 系统公告信息
 */
@Data
@TableName("notices")
@Schema(description = "系统公告信息")
public class Notice {

    @Schema(description = "公告ID，唯一标识", example = "1")
    @TableId(type = IdType.AUTO)
    private Long id; // 公告ID

    @Schema(description = "公告标题", example = "系统维护通知")
    private String title; // 公告标题

    @Schema(description = "公告内容详情", example = "系统将于今晚22:00-24:00进行维护升级，期间无法访问，请合理安排考试时间...")
    private String content; // 公告内容

    @Schema(description = "公告类型", example = "SYSTEM", allowableValues = {"SYSTEM", "FEATURE", "NOTICE"})
    private String type; // 公告类型：SYSTEM(系统)、FEATURE(新功能)、NOTICE(通知)

    @Schema(description = "优先级级别", example = "1", allowableValues = {"0", "1", "2"})
    private Integer priority; // 优先级：0-普通，1-重要，2-紧急

    @Schema(description = "是否启用显示", example = "true")
    private Boolean isActive; // 是否启用

    @Schema(description = "公告创建时间", example = "2024-01-15 10:00:00")
    private LocalDateTime createTime; // 创建时间

    @Schema(description = "公告更新时间", example = "2024-01-15 10:00:00")
    private LocalDateTime updateTime; // 更新时间

    // 手动添加getter和setter方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}