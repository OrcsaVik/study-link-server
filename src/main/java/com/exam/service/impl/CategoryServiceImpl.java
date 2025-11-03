/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.exam.entity.Category;
import com.exam.entity.Question;
import com.exam.mapper.CategoryMapper;
import com.exam.mapper.QuestionMapper;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Category> getCategoryTree() {
        // 获取所有分类
        List<Category> allCategories = categoryMapper.selectList(
                new LambdaQueryWrapper<Category>()
                        .orderByAsc(Category::getSort) // 按sort字段升序排序
        );

        // 获取并填充每个分类的题目数量
        fillQuestionCount(allCategories);

        // 构建树形结构
        return buildTree(allCategories);
    }

    @Override
    public List<Category> getAllCategories() {
        // 获取所有分类
        List<Category> categories = categoryMapper.selectList(
                new LambdaQueryWrapper<Category>()
                        .orderByAsc(Category::getSort));

        // 获取并填充每个分类的题目数量
        fillQuestionCount(categories);

        return categories;
    }

    @Override
    public void addCategory(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateById(category);
    }

    @Override
    public void deleteCategory(Long id) {
        // 检查是否为一级分类
        Category category = categoryMapper.selectById(id);
        if (category != null && category.getParentId() == 0) {
            throw new RuntimeException("不允许删除固定的一级分类");
        }

        // 检查是否有子分类
        Long childCount = categoryMapper.selectCount(
                new LambdaQueryWrapper<Category>()
                        .eq(Category::getParentId, id));
        if (childCount > 0) {
            throw new RuntimeException("该分类下有子分类，无法删除");
        }

        // 检查是否有题目
        Long questionCount = questionMapper.selectCount(
                new LambdaQueryWrapper<Question>()
                        .eq(Question::getCategoryId, id));
        if (questionCount > 0) {
            throw new RuntimeException("该分类下有题目，无法删除");
        }

        categoryMapper.deleteById(id);
    }

    private void fillQuestionCount(List<Category> categories) {
        // 获取每个分类的题目数量
        List<Map<String, Object>> questionCountList = questionMapper.getCategoryQuestionCount();

        // 将结果转换为Map<Long, Long>格式
        Map<Long, Long> questionCountMap = questionCountList.stream()
                .collect(Collectors.toMap(
                        map -> Long.valueOf(map.get("categoryId").toString()),
                        map -> Long.valueOf(map.get("count").toString())));

        // 设置题目数量
        categories.forEach(category -> category.setCount(questionCountMap.getOrDefault(category.getId(), 0L)));
    }

    // 构建树形结构
    private List<Category> buildTree(List<Category> categories) {
        // 按parentId分组
        Map<Long, List<Category>> childrenMap = categories.stream()
                .collect(Collectors.groupingBy(Category::getParentId));

        // 设置children属性，并从下至上汇总题目数量
        categories.forEach(category -> {
            List<Category> children = childrenMap.getOrDefault(category.getId(), new ArrayList<>());
            category.setChildren(children);

            // 汇总子分类的题目数量到父分类
            long childrenCount = children.stream()
                    .mapToLong(c -> c.getCount() != null ? c.getCount() : 0L)
                    .sum();
            long selfCount = category.getCount() != null ? category.getCount() : 0L;
            category.setCount(selfCount + childrenCount);
        });

        // 返回顶级分类（parentId = 0）
        return categories.stream()
                .filter(c -> c.getParentId() == 0)
                .collect(Collectors.toList());
    }
}