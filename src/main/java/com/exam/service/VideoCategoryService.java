/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.service;

import com.exam.entity.VideoCategory;
import java.util.List;

/**
 * 视频分类Service接口
 * 提供视频分类管理的业务逻辑操作
 */
public interface VideoCategoryService {

    /**
     * 获取分类树（包含视频数量统计）
     * @return 分类树列表
     */
    List<VideoCategory> getCategoryTree();

    /**
     * 获取所有分类列表
     * @return 分类列表
     */
    List<VideoCategory> getAllCategories();

    /**
     * 获取启用的顶级分类
     * @return 顶级分类列表
     */
    List<VideoCategory> getTopCategories();

    /**
     * 根据父级分类ID获取子分类
     * @param parentId 父级分类ID
     * @return 子分类列表
     */
    List<VideoCategory> getChildCategories(Long parentId);

    /**
     * 根据ID获取分类详情
     * @param id 分类ID
     * @return 分类信息
     */
    VideoCategory getCategoryById(Long id);

    /**
     * 添加分类
     * @param category 分类信息
     */
    void addCategory(VideoCategory category);

    /**
     * 更新分类
     * @param category 分类信息
     */
    void updateCategory(VideoCategory category);

    /**
     * 删除分类
     * @param id 分类ID
     */
    void deleteCategory(Long id);
}