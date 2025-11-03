/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS跨域配置类
 * 解决前后端分离开发中的跨域问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 配置CORS跨域
     * @param registry CORS注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径
                .allowedOriginPatterns("*") // 允许所有来源模式
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的HTTP方法
                .allowedHeaders("*") // 允许所有请求头
                .allowCredentials(false) // 不允许发送Cookie，避免与*冲突
                .maxAge(3600); // 预检请求的有效期，单位为秒
    }

    /**
     * CORS过滤器
     * @return CORS过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 允许所有来源模式
        configuration.addAllowedOriginPattern("*");

        // 允许所有HTTP方法
        configuration.addAllowedMethod("*");

        // 允许所有请求头
        configuration.addAllowedHeader("*");

        // 不允许发送Cookie，避免与*冲突
        configuration.setAllowCredentials(false);

        // 预检请求的有效期
        configuration.setMaxAge(3600L);

        // 暴露响应头
        configuration.addExposedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }

    /**
     * CORS配置源
     * @return CORS配置源
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // 允许所有来源
        configuration.addAllowedOriginPattern("*");

        // 允许所有HTTP方法
        configuration.addAllowedMethod("*");

        // 允许所有请求头
        configuration.addAllowedHeader("*");

        // 允许发送Cookie
        configuration.setAllowCredentials(true);

        // 预检请求的有效期
        configuration.setMaxAge(3600L);

        // 暴露响应头
        configuration.addExposedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}