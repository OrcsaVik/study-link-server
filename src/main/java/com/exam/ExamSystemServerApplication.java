/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam;

import org.apache.catalina.mbeans.ContextEnvironmentMBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 智能学习平台主启动类
 * 
 * 这是Spring Boot应用程序的入口点，包含以下关键功能：
 * 1. 通过@SpringBootApplication注解启用Spring Boot自动配置
 * 2. 通过@MapperScan注解扫描并注册MyBatis Mapper接口
 * 3. 启动内嵌的Tomcat服务器，提供Web服务
 * 
 * Spring Boot教学要点：
 * - @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
 * - 自动配置会根据classpath中的依赖自动配置Spring应用
 * - 内嵌服务器让部署变得简单，无需外部Tomcat
 * 
 * @author 智能学习平台开发团队
 * @version 1.0
 * @since 2024-01-01
 */
@SpringBootApplication // Spring Boot核心注解，启用自动配置、组件扫描等功能
@MapperScan("com.exam.mapper") // 扫描指定包下的MyBatis Mapper接口，自动注册为Spring Bean
public class ExamSystemServerApplication {

    // 1. 修正拼写错误（server.port），且不使用静态变量（或通过setter注入静态变量）
    // 这里推荐通过上下文获取，更适合main方法场景
    private static String port;

    // 2. 非静态setter方法注入静态变量（解决静态变量无法直接注入问题）
    @Value("${server.port}")
    public void setPort(String port) {
        ExamSystemServerApplication.port = port;
    }

    public static void main(String[] args) {
        // 启动应用并获取上下文
        ConfigurableApplicationContext context = SpringApplication.run(ExamSystemServerApplication.class, args);

        // 备选方案：从上下文直接获取端口（无需依赖@Value，更推荐）
        // port = context.getEnvironment().getProperty("server.port");

        String baseUrl = "http://localhost:%s";
        // 3. 修正输出语句的格式化
        System.out.println("=================================");
        System.out.println(String.format("🎓 智能学习平台启动成功！%s", String.format(baseUrl, port)));
        System.out.println(String.format("📖 访问地址：%s", String.format(baseUrl, port))); // 补充端口
        System.out.println("💡 技术栈：Spring Boot + MyBatis Plus + MySQL");
        System.out.println("=================================");
    }
}