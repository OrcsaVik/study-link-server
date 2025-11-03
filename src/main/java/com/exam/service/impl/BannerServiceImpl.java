/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exam.common.Result;
import com.exam.entity.Banner;
import com.exam.mapper.BannerMapper;
import com.exam.service.BannerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 轮播图服务实现类
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    @Override
    public Result<List<Banner>> getActiveBanners() {
        try {
            List<Banner> banners = baseMapper.selectActiveBanners();
            return Result.success(banners);
        } catch (Exception e) {
            return Result.error("获取轮播图失败：" + e.getMessage());
        }
    }

    @Override
    public Result<List<Banner>> getAllBanners() {
        try {
            QueryWrapper<Banner> wrapper = new QueryWrapper<>();
            wrapper.orderByAsc("sort_order");
            List<Banner> banners = this.list(wrapper);
            return Result.success(banners);
        } catch (Exception e) {
            return Result.error("获取轮播图列表失败：" + e.getMessage());
        }
    }

    @Override
    public Result<String> addBanner(Banner banner) {
        try {
            banner.setCreateTime(LocalDateTime.now());
            banner.setUpdateTime(LocalDateTime.now());
            if (banner.getIsActive() == null) {
                banner.setIsActive(true); // 默认启用
            }
            if (banner.getSortOrder() == null) {
                banner.setSortOrder(0); // 默认排序
            }

            boolean success = this.save(banner);
            if (success) {
                return Result.success("轮播图添加成功");
            } else {
                return Result.error("轮播图添加失败");
            }
        } catch (Exception e) {
            return Result.error("轮播图添加失败：" + e.getMessage());
        }
    }

    @Override
    public Result<String> updateBanner(Banner banner) {
        try {
            banner.setUpdateTime(LocalDateTime.now());
            boolean success = this.updateById(banner);
            if (success) {
                return Result.success("轮播图更新成功");
            } else {
                return Result.error("轮播图更新失败");
            }
        } catch (Exception e) {
            return Result.error("轮播图更新失败：" + e.getMessage());
        }
    }

    @Override
    public Result<String> deleteBanner(Long id) {
        try {
            boolean success = this.removeById(id);
            if (success) {
                return Result.success("轮播图删除成功");
            } else {
                return Result.error("轮播图删除失败");
            }
        } catch (Exception e) {
            return Result.error("轮播图删除失败：" + e.getMessage());
        }
    }

    @Override
    public Result<String> toggleBannerStatus(Long id, Boolean isActive) {
        try {
            Banner banner = new Banner();
            banner.setId(id);
            banner.setIsActive(isActive);
            banner.setUpdateTime(LocalDateTime.now());

            boolean success = this.updateById(banner);
            if (success) {
                String status = isActive ? "启用" : "禁用";
                return Result.success("轮播图" + status + "成功");
            } else {
                return Result.error("轮播图状态更新失败");
            }
        } catch (Exception e) {
            return Result.error("轮播图状态更新失败：" + e.getMessage());
        }
    }
}