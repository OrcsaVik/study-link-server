/**
版权所有 © [2025] 广东财经大学

特此授予任何获得本软件及相关文档文件（以下简称“软件”）副本的人免费许可，允许其无限制地处理本软件，包括但不限于使用、复制、修改、合并、发布、分发、再许可和/或出售软件副本的权利，以及允许向其提供软件的人享有同等权利，但须符合以下条件：

上述版权声明和本许可声明应包含在软件的所有副本或主要部分中。

本软件按“原样”提供，不提供任何明示或暗示的担保，包括但不限于对适销性、特定用途适用性和非侵权性的担保。在任何情况下，版权持有人或贡献者均不对因使用本软件或与本软件使用相关的任何索赔、损害或其他责任承担责任，无论是基于合同、侵权行为或其他法律理论。

注：本文件包含来源于尚硅谷（Sunline Education）的原始代码, 如果感兴趣源码，请到尚硅谷的网站进行查询。
*/


package com.exam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件访问控制器
 * 提供本地上传文件的HTTP访问服务
 */
@Slf4j
@RestController
@RequestMapping("/files")
@CrossOrigin
public class FileController {

    @Value("${file.upload.path:./static/}") // 本地文件存储路径
    private String localUploadPath;

    /**
     * 访问上传的文件
     * @param filePath 文件路径（如：banners/2024/06/25/abc123.jpg）
     * @param response HTTP响应
     */
    @GetMapping("/**")
    public void getFile(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取文件路径 // 提取请求的文件路径
            String requestURI = request.getRequestURI();
            String filePath = requestURI.substring("/files/".length());

            // 构建完整文件路径 // 构建本地文件路径
            Path fullPath = Paths.get(localUploadPath, filePath);
            File file = fullPath.toFile();

            // 检查文件是否存在 // 验证文件存在
            if (!file.exists() || !file.isFile()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // 检查文件是否在允许的目录内（安全检查） // 防止路径穿越攻击
            String canonicalFilePath = file.getCanonicalPath();
            String canonicalUploadPath = new File(localUploadPath).getCanonicalPath();
            if (!canonicalFilePath.startsWith(canonicalUploadPath)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            // 设置响应头 // 配置HTTP响应
            String contentType = Files.probeContentType(fullPath);
            if (contentType == null) {
                contentType = "application/octet-stream"; // 默认类型
            }
            response.setContentType(contentType);
            response.setContentLength((int) file.length());

            // 设置缓存头 // 优化缓存策略
            response.setHeader("Cache-Control", "public, max-age=86400"); // 缓存1天

            // 输出文件内容 // 返回文件数据
            try (
                    FileInputStream fis = new FileInputStream(file);
                    OutputStream os = response.getOutputStream()) {

                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
            }

            log.debug("文件访问成功: {}", filePath);

        } catch (IOException e) {
            log.error("文件访问失败: {}", e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}