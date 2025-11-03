/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.common;

/**
 * 缓存常量类
 * 定义系统中使用的缓存名称和key前缀
 */
public class CacheConstants {

    /**
     * 题目模块缓存名称
     */
    public static final String QUESTION_CACHE = "question";

    /**
     * 试卷模块缓存名称
     */
    public static final String PAPER_CACHE = "paper";

    /**
     * 考试记录模块缓存名称
     */
    public static final String EXAM_RECORD_CACHE = "exam_record";

    /**
     * 题目详情缓存key前缀
     */
    public static final String QUESTION_DETAIL_KEY = "question:detail:";

    /**
     * 分类题目列表缓存key前缀
     */
    public static final String QUESTION_CATEGORY_KEY = "question:category:";

    /**
     * 试卷详情缓存key前缀
     */
    public static final String PAPER_DETAIL_KEY = "paper:detail:";

    /**
     * 考试记录详情缓存key前缀
     */
    public static final String EXAM_RECORD_DETAIL_KEY = "exam_record:detail:";

    /**
     * 热门题目缓存key
     */
    public static final String POPULAR_QUESTIONS_KEY = "question:popular";

    /**
     * 题目访问计数key
     */
    public static final String QUESTION_VIEW_COUNT_KEY = "question:view_count";

    /**
     * 热门题目数量
     */
    public static final int POPULAR_QUESTIONS_COUNT = 10;

    /**
     * 缓存过期时间（秒）
     */
    public static final long DEFAULT_EXPIRE_SECONDS = 1800; // 30分钟

    /**
     * 热点数据缓存过期时间（秒）
     */
    public static final long HOT_DATA_EXPIRE_SECONDS = 3600; // 1小时
}