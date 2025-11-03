/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.VideoCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;
import java.util.Map;

/**
 * 视频分类Mapper接口
 * 提供视频分类相关的数据访问操作
 */
@Mapper
public interface VideoCategoryMapper extends BaseMapper<VideoCategory> {

    /**
     * 获取每个分类的视频数量统计
     * @return 包含分类ID和视频数量的结果列表
     */
    @Select("SELECT category_id, COUNT(*) as video_count FROM videos WHERE status = 1 GROUP BY category_id")
    @Results({
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "videoCount", column = "video_count")
    })
    List<Map<String, Object>> getCategoryVideoCount();

    /**
     * 获取所有启用的顶级分类
     * @return 顶级分类列表
     */
    @Select("SELECT * FROM video_categories WHERE parent_id = 0 AND status = 1 ORDER BY sort_order ASC")
    List<VideoCategory> getTopCategories();

    /**
     * 根据父级分类ID获取子分类
     * @param parentId 父级分类ID
     * @return 子分类列表
     */
    @Select("SELECT * FROM video_categories WHERE parent_id = #{parentId} AND status = 1 ORDER BY sort_order ASC")
    List<VideoCategory> getChildCategories(Long parentId);
}