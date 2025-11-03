/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.Video;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 视频信息Mapper接口
 * 提供视频相关的复杂数据访问操作
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 分页查询已发布的视频列表（包含分类名称）
     * @param page 分页对象
     * @param categoryId 分类ID（可选）
     * @param keyword 搜索关键字（可选）
     * @return 视频分页结果
     */
    @Select("<script>" +
            "SELECT v.*, vc.name as category_name " +
            "FROM videos v " +
            "LEFT JOIN video_categories vc ON v.category_id = vc.id " +
            "WHERE v.status = 1 " +
            "<if test='categoryId != null'> AND v.category_id = #{categoryId} </if>" +
            "<if test='keyword != null and keyword != \"\"'> " +
            "AND (v.title LIKE CONCAT('%', #{keyword}, '%') " +
            "OR v.tags LIKE CONCAT('%', #{keyword}, '%')) " +
            "</if>" +
            "ORDER BY v.created_at DESC" +
            "</script>")
    IPage<Video> getPublishedVideosPage(Page<?> page,
                                        @Param("categoryId") Long categoryId,
                                        @Param("keyword") String keyword);

    /**
     * 管理端分页查询视频列表（包含分类名称和审核管理员信息）
     * @param page 分页对象
     * @param status 状态筛选（可选）
     * @param uploaderType 上传者类型筛选（可选）
     * @param keyword 搜索关键字（可选）
     * @return 视频分页结果
     */
    @Select("<script>" +
            "SELECT v.*, vc.name as category_name, u.real_name as audit_admin_name " +
            "FROM videos v " +
            "LEFT JOIN video_categories vc ON v.category_id = vc.id " +
            "LEFT JOIN users u ON v.audit_admin_id = u.id " +
            "WHERE 1=1 " +
            "<if test='status != null'> AND v.status = #{status} </if>" +
            "<if test='uploaderType != null'> AND v.uploader_type = #{uploaderType} </if>" +
            "<if test='keyword != null and keyword != \"\"'> " +
            "AND (v.title LIKE CONCAT('%', #{keyword}, '%') " +
            "OR v.uploader_name LIKE CONCAT('%', #{keyword}, '%')) " +
            "</if>" +
            "ORDER BY v.created_at DESC" +
            "</script>")
    IPage<Video> getVideosForAdmin(Page<?> page,
                                   @Param("status") Integer status,
                                   @Param("uploaderType") Integer uploaderType,
                                   @Param("keyword") String keyword);

    /**
     * 获取热门视频列表（按观看次数排序）
     * @param limit 限制数量
     * @return 热门视频列表
     */
    @Select("SELECT v.*, vc.name as category_name " +
            "FROM videos v " +
            "LEFT JOIN video_categories vc ON v.category_id = vc.id " +
            "WHERE v.status = 1 " +
            "ORDER BY v.view_count DESC " +
            "LIMIT #{limit}")
    List<Video> getPopularVideos(@Param("limit") Integer limit);

    /**
     * 获取最新视频列表
     * @param limit 限制数量
     * @return 最新视频列表
     */
    @Select("SELECT v.*, vc.name as category_name " +
            "FROM videos v " +
            "LEFT JOIN video_categories vc ON v.category_id = vc.id " +
            "WHERE v.status = 1 " +
            "ORDER BY v.created_at DESC " +
            "LIMIT #{limit}")
    List<Video> getLatestVideos(@Param("limit") Integer limit);

    /**
     * 获取视频统计信息
     * @return 统计数据
     */
    @Select("SELECT " +
            "COUNT(*) as total_count, " +
            "COUNT(CASE WHEN status = 0 THEN 1 END) as pending_count, " +
            "COUNT(CASE WHEN status = 1 THEN 1 END) as published_count, " +
            "COUNT(CASE WHEN status = 2 THEN 1 END) as rejected_count, " +
            "COUNT(CASE WHEN uploader_type = 1 THEN 1 END) as user_upload_count, " +
            "COUNT(CASE WHEN uploader_type = 2 THEN 1 END) as admin_upload_count " +
            "FROM videos")
    Map<String, Object> getVideoStatistics();

    /**
     * 增加视频观看次数
     * @param videoId 视频ID
     * @return 更新行数
     */
    @Update("UPDATE videos SET view_count = view_count + 1 WHERE id = #{videoId}")
    int incrementViewCount(@Param("videoId") Long videoId);

    /**
     * 增加视频点赞次数
     * @param videoId 视频ID
     * @return 更新行数
     */
    @Update("UPDATE videos SET like_count = like_count + 1 WHERE id = #{videoId}")
    int incrementLikeCount(@Param("videoId") Long videoId);

    /**
     * 减少视频点赞次数
     * @param videoId 视频ID
     * @return 更新行数
     */
    @Update("UPDATE videos SET like_count = like_count - 1 WHERE id = #{videoId} AND like_count > 0")
    int decrementLikeCount(@Param("videoId") Long videoId);
}