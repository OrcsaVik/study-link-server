/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 真题分类实体类，对应question_categories表
 */
@Data
public class QuestionCategory {

    /** 分类ID */
    private Integer id;
    /** 分类名称 */
    private String name;
    /** 父分类ID，0表示顶级分类 */
    private Integer parentId;
    /** 分类层级：1-一级分类 2-二级分类 */
    private Integer level;
    /** 排序序号 */
    private Integer sortOrder;
    /** 分类描述 */
    private String description;
    /** 状态：0-禁用 1-启用 */
    private Integer status;
    /** 创建时间 */
    private Date createdTime;
    /** 更新时间 */
    private Date updatedTime;

    /** 子分类列表（用于树形结构展示，不对应数据库字段） */
    private List<QuestionCategory> children;
}