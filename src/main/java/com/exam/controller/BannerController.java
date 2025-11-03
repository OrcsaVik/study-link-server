/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.controller;

import com.exam.common.Result;
import com.exam.entity.Banner;
import com.exam.service.BannerService;
import com.exam.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 轮播图控制器 - 处理轮播图管理相关的HTTP请求
 * 包括图片上传、轮播图的CRUD操作、状态切换等功能
 */
@RestController // REST控制器，返回JSON数据
@RequestMapping("/api/banners") // 轮播图API路径前缀
@CrossOrigin // 允许跨域访问
@Tag(name = "轮播图管理", description = "轮播图相关操作，包括图片上传、轮播图增删改查、状态管理等功能") // Swagger API分组
public class BannerController {

    /**
     * 注入轮播图业务服务
     */
    @Autowired
    private BannerService bannerService;

    /**
     * 注入文件上传服务
     */
    @Autowired
    private FileUploadService fileUploadService; // 文件上传服务

    /**
     * 上传轮播图图片
     * @param file 图片文件
     * @return 图片访问URL
     */
    @PostMapping("/upload-image") // 处理POST请求
    @Operation(summary = "上传轮播图图片", description = "将图片文件上传到MinIO服务器，返回可访问的图片URL") // API描述
    public Result<String> uploadBannerImage(
                                            @Parameter(description = "要上传的图片文件，支持jpg、png、gif等格式，大小限制5MB") @RequestParam("file") MultipartFile file) {
        try {
            // 验证文件类型 // 检查文件格式
            if (file.isEmpty()) {
                return Result.error("请选择要上传的文件");
            }

            // 检查文件类型是否为图片 // 验证图片格式
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return Result.error("只能上传图片文件");
            }

            // 检查文件大小（限制为5MB） // 验证文件大小
            if (file.getSize() > 5 * 1024 * 1024) {
                return Result.error("图片文件大小不能超过5MB");
            }

            // 上传文件到MinIO // 执行文件上传
            Map<String, Object> uploadResult = fileUploadService.uploadFile(file, "banners");
            String imageUrl = (String) uploadResult.get("url"); // 从结果中提取URL

            return Result.success(imageUrl, "图片上传成功");

        } catch (Exception e) {
            return Result.error("图片上传失败: " + e.getMessage());
        }
    }

    /**
     * 获取启用的轮播图（前台首页使用）
     * @return 轮播图列表
     */
    @GetMapping("/active") // 处理GET请求
    @Operation(summary = "获取启用的轮播图", description = "获取状态为启用的轮播图列表，供前台首页展示使用") // API描述
    public Result<List<Banner>> getActiveBanners() {
        return bannerService.getActiveBanners();
    }

    /**
     * 获取所有轮播图（管理后台使用）
     * @return 轮播图列表
     */
    @GetMapping("/list") // 处理GET请求
    @Operation(summary = "获取所有轮播图", description = "获取所有轮播图列表，包括启用和禁用的，供管理后台使用") // API描述
    public Result<List<Banner>> getAllBanners() {
        return bannerService.getAllBanners();
    }

    /**
     * 根据ID获取轮播图
     * @param id 轮播图ID
     * @return 轮播图详情
     */
    @GetMapping("/{id}") // 处理GET请求
    @Operation(summary = "根据ID获取轮播图", description = "根据轮播图ID获取单个轮播图的详细信息") // API描述
    public Result<Banner> getBannerById(@Parameter(description = "轮播图ID") @PathVariable Long id) {
        Banner banner = bannerService.getById(id);
        if (banner != null) {
            return Result.success(banner);
        } else {
            return Result.error("轮播图不存在");
        }
    }

    /**
     * 添加轮播图
     * @param banner 轮播图对象
     * @return 操作结果
     */
    @PostMapping("/add") // 处理POST请求
    @Operation(summary = "添加轮播图", description = "创建新的轮播图，需要提供图片URL、标题、跳转链接等信息") // API描述
    public Result<String> addBanner(@RequestBody Banner banner) {
        return bannerService.addBanner(banner);
    }

    /**
     * 更新轮播图
     * @param banner 轮播图对象
     * @return 操作结果
     */
    @PutMapping("/update") // 处理PUT请求
    @Operation(summary = "更新轮播图", description = "更新轮播图的信息，包括图片、标题、跳转链接、排序等") // API描述
    public Result<String> updateBanner(@RequestBody Banner banner) {
        return bannerService.updateBanner(banner);
    }

    /**
     * 删除轮播图
     * @param id 轮播图ID
     * @return 操作结果
     */
    @DeleteMapping("/delete/{id}") // 处理DELETE请求
    @Operation(summary = "删除轮播图", description = "根据ID删除指定的轮播图") // API描述
    public Result<String> deleteBanner(@Parameter(description = "轮播图ID") @PathVariable Long id) {
        return bannerService.deleteBanner(id);
    }

    /**
     * 启用/禁用轮播图
     * @param id 轮播图ID
     * @param isActive 是否启用
     * @return 操作结果
     */
    @PutMapping("/toggle/{id}") // 处理PUT请求
    @Operation(summary = "切换轮播图状态", description = "启用或禁用指定的轮播图，禁用后不会在前台显示") // API描述
    public Result<String> toggleBannerStatus(
                                             @Parameter(description = "轮播图ID") @PathVariable Long id,
                                             @Parameter(description = "是否启用，true为启用，false为禁用") @RequestParam Boolean isActive) {
        return bannerService.toggleBannerStatus(id, isActive);
    }
}