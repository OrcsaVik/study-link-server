<h1 align="center">📚 Study Link Server</h1>

<p align="center">
  <b>智能考试 / 学习系统后端服务</b><br>
  基于 Spring Boot 构建，提供 AI 判卷、缓存加速、文件存储、接口调试等完整功能。
</p>

---

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-orange?logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/SpringBoot-3.x-brightgreen?logo=springboot"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Redis-7.2+-red?logo=redis&logoColor=white"/>
  <img src="https://img.shields.io/badge/MinIO-Storage-yellow?logo=minio&logoColor=white"/>
  <img src="https://img.shields.io/badge/Kimi-AI%20Judge-purple?logo=openaigym&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-Build%20Tool-lightgrey?logo=apachemaven"/>
</p>

---

## 🧭 项目简介

**study-link-server** 是智能考试 / 学习系统的 **后端核心服务**，负责管理题库、试卷、判卷、用户数据与文件上传等功能。  
该项目为前端 Web 端和移动端提供统一的 RESTful API 支持。

---

## 🧩 技术栈总览

| 模块 | 技术选型 | 说明 |
|------|------------|------|
| 核心框架 | Spring Boot 3.x | 后端主框架 |
| 数据访问 | MyBatis Plus | ORM 框架 |
| 数据库 | MySQL 8.0+ | 结构化数据存储 |
| 缓存 | Redis 7.2+ | 热点数据缓存 |
| 文件存储 | MinIO | 私有化文件服务 |
| AI 判卷 | Kimi API | 智能主观题评分 |
| 文档系统 | Knife4j | 在线接口调试 |

---

## ⚙️ 环境准备

**基础环境**
- ☕ JDK 17+
- 🔧 Maven 3.6+

**依赖服务**
- 🐬 MySQL 8.0+
- 🔴 Redis 7.2+
- 🗄️ MinIO

**配置调整**
1. 修改 `application.yml` 中数据库、Redis、MinIO 的连接参数。
2. 在配置中填入有效的 `Kimi API Key`（建议使用环境变量注入）。

---

## 🚀 启动步骤

### 1️⃣ 编译项目
```bash
# 进入项目根目录
cd study-link-server

# 编译打包（跳过测试）
mvn clean package -Dmaven.test.skip=true
2️⃣ 启动服务
方式一：Jar 包启动
bash
复制代码
cd target
java -jar study-link-server-1.0.0.jar
方式二：IDE 启动
导入项目至 IntelliJ IDEA 或 Eclipse

找到主类 com.exam.StudyLinkServerApplication

右键运行 Run / Debug

3️⃣ 验证启动
🌐 访问接口文档：http://localhost:8000/doc.html

🧮 数据库会自动初始化（若启用 Flyway/Liquibase）

🧠 核心功能
📘 API 文档：基于 Knife4j 的可视化接口调试界面

🤖 AI 判卷：集成 Kimi 大模型，自动评分主观题

📦 文件存储：使用 MinIO 管理上传、下载与预签名链接

⚡ 缓存支持：Redis 提升访问性能与并发处理能力

⚠️ 注意事项
启动项目前，请确认 MySQL、Redis、MinIO 服务已正确运行

Kimi API 密钥请勿硬编码，可通过系统环境变量或配置中心注入

开发环境日志级别为 debug，生产建议切换为 info 或 warn

<p align="center"> <img src="https://img.shields.io/badge/License-MIT-blue?style=flat-square"/> <img src="https://img.shields.io/github/stars/yourname/study-link-server?style=social"/> </p> <p align="center"> 💡 让 AI 帮你更聪明地学习与考试！ </p> ```
