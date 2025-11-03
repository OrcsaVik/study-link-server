/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.controller;

import com.exam.common.Result;
import com.exam.entity.Category;
import com.exam.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器 - 处理题目分类管理相关的HTTP请求
 * 包括分类的增删改查、树形结构展示等功能
 */
@RestController // REST控制器，返回JSON数据
@RequestMapping("/api/categories") // 分类API路径前缀
@Tag(name = "分类管理", description = "题目分类相关操作，包括分类的增删改查、树形结构管理等功能") // Swagger API分组
public class CategoryController {

    /**
     * 注入分类业务服务
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类列表（包含题目数量）
     * @return 分类列表数据
     */
    @GetMapping // 处理GET请求
    @Operation(summary = "获取分类列表", description = "获取所有题目分类列表，包含每个分类下的题目数量统计") // API描述
    public Result<List<Category>> getCategories() {
        return Result.success(categoryService.getAllCategories());
    }

    /**
     * 获取分类树形结构
     * @return 分类树数据
     */
    @GetMapping("/tree") // 处理GET请求
    @Operation(summary = "获取分类树形结构", description = "获取题目分类的树形层级结构，用于前端树形组件展示") // API描述
    public Result<List<Category>> getCategoryTree() {
        return Result.success(categoryService.getCategoryTree());
    }

    /**
     * 添加分类
     * @param category 分类对象
     * @return 操作结果
     */
    @PostMapping // 处理POST请求
    @Operation(summary = "添加新分类", description = "创建新的题目分类，支持设置父分类实现层级结构") // API描述
    public Result<Void> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return Result.success(null);
    }

    /**
     * 更新分类
     * @param category 分类对象
     * @return 操作结果
     */
    @PutMapping // 处理PUT请求
    @Operation(summary = "更新分类信息", description = "修改分类的名称、描述、排序等信息") // API描述
    public Result<Void> updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
        return Result.success(null);
    }

    /**
     * 删除分类
     * @param id 分类ID
     * @return 操作结果
     */
    @DeleteMapping("/{id}") // 处理DELETE请求
    @Operation(summary = "删除分类", description = "删除指定的题目分类，注意：删除前需确保分类下没有题目") // API描述
    public Result<Void> deleteCategory(
                                       @Parameter(description = "分类ID") @PathVariable Long id) {
        categoryService.deleteCategory(id);
        return Result.success(null);
    }
}