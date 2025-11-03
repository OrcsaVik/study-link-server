/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * 题目类型的枚举
 */
public enum QuestionType {

    /**
     * 选择题
     */
    CHOICE,
    /**
     * 判断题
     */
    JUDGE,
    /**
     * 简答题
     */
    TEXT;

    /**
     * 自定义一个创建方法，用于Jackson反序列化
     * 这使得将字符串转换为枚举的过程更加健壮，可以忽略大小写
     * @param value 前端传来的字符串值
     * @return 对应的枚举常量，如果找不到则返回null或抛出异常
     */
    @JsonCreator
    public static QuestionType fromString(String value) {
        if (value == null) {
            return null;
        }
        for (QuestionType type : QuestionType.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        // 如果严格要求必须匹配，可以抛出异常
        // throw new IllegalArgumentException("未知的题目类型: " + value);
        return null; // 或者返回null，让后续的校验逻辑处理
    }
}