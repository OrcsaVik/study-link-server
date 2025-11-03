/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.VideoLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

/**
 * 视频点赞Mapper接口
 * 提供视频点赞相关的数据访问操作
 */
@Mapper
public interface VideoLikeMapper extends BaseMapper<VideoLike> {

    /**
     * 检查用户是否已点赞该视频（基于IP）
     * @param videoId 视频ID
     * @param userIp 用户IP
     * @return 是否已点赞
     */
    @Select("SELECT COUNT(*) > 0 FROM video_likes WHERE video_id = #{videoId} AND user_ip = #{userIp}")
    boolean isLikedByIp(@Param("videoId") Long videoId, @Param("userIp") String userIp);

    /**
     * 获取视频的点赞总数
     * @param videoId 视频ID
     * @return 点赞总数
     */
    @Select("SELECT COUNT(*) FROM video_likes WHERE video_id = #{videoId}")
    Long getLikeCountByVideoId(@Param("videoId") Long videoId);
}