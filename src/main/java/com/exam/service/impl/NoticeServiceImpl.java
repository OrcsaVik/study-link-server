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
import com.exam.entity.Notice;
import com.exam.mapper.NoticeMapper;
import com.exam.service.NoticeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公告服务实现类
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public Result<List<Notice>> getActiveNotices() {
        try {
            List<Notice> notices = baseMapper.selectActiveNotices();
            return Result.success(notices);
        } catch (Exception e) {
            return Result.error("获取公告失败：" + e.getMessage());
        }
    }

    @Override
    public Result<List<Notice>> getLatestNotices(int limit) {
        try {
            List<Notice> notices = baseMapper.selectLatestNotices(limit);
            return Result.success(notices);
        } catch (Exception e) {
            return Result.error("获取最新公告失败：" + e.getMessage());
        }
    }

    @Override
    public Result<List<Notice>> getAllNotices() {
        try {
            QueryWrapper<Notice> wrapper = new QueryWrapper<>();
            wrapper.orderByDesc("priority", "create_time");
            List<Notice> notices = this.list(wrapper);
            return Result.success(notices);
        } catch (Exception e) {
            return Result.error("获取公告列表失败：" + e.getMessage());
        }
    }

    @Override
    public Result<String> addNotice(Notice notice) {
        try {
            notice.setCreateTime(LocalDateTime.now());
            notice.setUpdateTime(LocalDateTime.now());
            if (notice.getIsActive() == null) {
                notice.setIsActive(true); // 默认启用
            }
            if (notice.getPriority() == null) {
                notice.setPriority(0); // 默认普通优先级
            }
            if (notice.getType() == null) {
                notice.setType("NOTICE"); // 默认通知类型
            }

            boolean success = this.save(notice);
            if (success) {
                return Result.success("公告添加成功");
            } else {
                return Result.error("公告添加失败");
            }
        } catch (Exception e) {
            return Result.error("公告添加失败：" + e.getMessage());
        }
    }

    @Override
    public Result<String> updateNotice(Notice notice) {
        try {
            notice.setUpdateTime(LocalDateTime.now());
            boolean success = this.updateById(notice);
            if (success) {
                return Result.success("公告更新成功");
            } else {
                return Result.error("公告更新失败");
            }
        } catch (Exception e) {
            return Result.error("公告更新失败：" + e.getMessage());
        }
    }

    @Override
    public Result<String> deleteNotice(Long id) {
        try {
            boolean success = this.removeById(id);
            if (success) {
                return Result.success("公告删除成功");
            } else {
                return Result.error("公告删除失败");
            }
        } catch (Exception e) {
            return Result.error("公告删除失败：" + e.getMessage());
        }
    }

    @Override
    public Result<String> toggleNoticeStatus(Long id, Boolean isActive) {
        try {
            Notice notice = new Notice();
            notice.setId(id);
            notice.setIsActive(isActive);
            notice.setUpdateTime(LocalDateTime.now());

            boolean success = this.updateById(notice);
            if (success) {
                String status = isActive ? "启用" : "禁用";
                return Result.success("公告" + status + "成功");
            } else {
                return Result.error("公告状态更新失败");
            }
        } catch (Exception e) {
            return Result.error("公告状态更新失败：" + e.getMessage());
        }
    }
}