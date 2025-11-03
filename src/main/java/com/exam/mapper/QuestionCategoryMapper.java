/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.mapper;

import com.exam.entity.QuestionCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 真题分类Mapper接口
 */
@Mapper
public interface QuestionCategoryMapper {

    /**
     * 查询所有分类（用于树形展示）
     * @return 分类列表
     */
    List<QuestionCategory> selectAll();

    /**
     * 根据父级ID查询子分类
     * @param parentId 父级ID
     * @return 子分类列表
     */
    List<QuestionCategory> selectByParentId(@Param("parentId") Integer parentId);

    /**
     * 根据ID查询分类详情
     * @param id 分类ID
     * @return 分类信息
     */
    QuestionCategory selectById(@Param("id") Integer id);

    /**
     * 新增分类
     * @param category 分类信息
     * @return 影响行数
     */
    int insert(QuestionCategory category);

    /**
     * 更新分类信息
     * @param category 分类信息
     * @return 影响行数
     */
    int update(QuestionCategory category);

    /**
     * 删除分类
     * @param id 分类ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);

    /**
     * 查询所有启用状态的分类（用于下拉选择）
     * @return 分类列表
     */
    List<QuestionCategory> selectAllEnabled();

    /**
     * 检查分类名称是否已存在（同级别下）
     * @param name 分类名称
     * @param parentId 父级ID
     * @param excludeId 排除的ID（用于更新时检查）
     * @return 存在的数量
     */
    int checkNameExists(@Param("name") String name,
                        @Param("parentId") Integer parentId,
                        @Param("excludeId") Integer excludeId);

    /**
     * 检查分类下是否有子分类
     * @param parentId 父级ID
     * @return 子分类数量
     */
    int countChildren(@Param("parentId") Integer parentId);

    /**
     * 检查分类下是否有题目
     * @param categoryId 分类ID
     * @return 题目数量
     */
    int countQuestions(@Param("categoryId") Integer categoryId);

    /**
     * 更新排序序号
     * @param id 分类ID
     * @param sortOrder 排序序号
     * @return 影响行数
     */
    int updateSortOrder(@Param("id") Integer id, @Param("sortOrder") Integer sortOrder);
}