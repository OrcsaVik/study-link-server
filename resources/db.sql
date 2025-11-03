-- MySQL dump 10.13  Distrib 8.4.0, for Win64 (x86_64)
--
-- Host: 192.168.33.10    Database: exam_system
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer_record`
--

DROP TABLE IF EXISTS `answer_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_record_id` int NOT NULL,
  `question_id` int NOT NULL,
  `user_answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `score` int DEFAULT '0',
  `is_correct` int DEFAULT '0',
  `ai_correction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_record`
--

LOCK TABLES `answer_record` WRITE;
/*!40000 ALTER TABLE `answer_record` DISABLE KEYS */;
INSERT INTO `answer_record` VALUES (231,41,66,'T',5,1,NULL,'2025-07-30 18:55:11','2025-07-30 18:55:11',0),(232,41,68,'F',0,0,NULL,'2025-07-30 18:55:11','2025-07-30 18:55:11',0),(233,41,69,'阿达大',0,0,'答案与题目要求不符，完全错误','2025-07-30 18:55:11','2025-07-30 18:55:13',0),(234,41,71,'A',0,0,NULL,'2025-07-30 18:55:11','2025-07-30 18:55:13',0),(235,41,72,'A',0,0,NULL,'2025-07-30 18:55:11','2025-07-30 18:55:13',0),(236,41,73,'阿达大',0,0,'答案与题目要求不符','2025-07-30 18:55:11','2025-07-30 18:55:15',0),(237,41,74,'T',5,1,NULL,'2025-07-30 18:55:11','2025-07-30 18:55:15',0),(238,41,75,'B',0,0,NULL,'2025-07-30 18:55:11','2025-07-30 18:55:15',0),(239,41,76,'A,B',0,0,NULL,'2025-07-30 18:55:11','2025-07-30 18:55:15',0),(240,41,77,'A',0,0,NULL,'2025-07-30 18:55:11','2025-07-30 18:55:15',0),(241,41,78,'B',0,0,NULL,'2025-07-30 18:55:11','2025-07-30 18:55:15',0),(242,41,79,'B',0,0,NULL,'2025-07-30 18:55:11','2025-07-30 18:55:15',0);
/*!40000 ALTER TABLE `answer_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banners`
--

DROP TABLE IF EXISTS `banners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banners` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '轮播图ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '轮播图标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '轮播图描述',
  `image_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片URL',
  `link_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '跳转链接',
  `sort_order` int DEFAULT '0' COMMENT '排序顺序',
  `is_active` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sort_order` (`sort_order`) USING BTREE,
  KEY `idx_is_active` (`is_active`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='轮播图表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banners`
--

LOCK TABLES `banners` WRITE;
/*!40000 ALTER TABLE `banners` DISABLE KEYS */;
INSERT INTO `banners` VALUES (4,'Java+AI应用开发','以Java+大模型应用为核，广度延伸至大数据&人工智能！','http://47.94.86.115:9000/java1229/banners/2025/07/18/c65f24222a724d0db9a481a46cd3b64a.png','https://www.atguigu.com/java/',1,1,'2025-06-25 18:27:00','2025-07-24 16:56:02',1),(5,'嵌入式&万物互联','大牛手把手教你实操企业真实项目，无缝对接企业真实工作流程','http://47.94.86.115:9000/java1229/banners/2025/07/18/538d9315bcaa48599fa58f59682e3b75.png','https://www.atguigu.com/iot/',2,1,'2025-06-25 18:27:00','2025-07-23 18:53:20',0),(6,'人工智能大模型','全球人工智能浪潮正劲，时代红利，先到先得！','http://47.94.86.115:9000/java1229/banners/2025/07/18/c041fd1e11ff4bc09aa5831c4cb08e5b.png','https://www.atguigu.com/ai/',3,1,'2025-06-25 18:27:00','2025-07-23 19:05:59',0),(7,'AI智能运维','BAT级实战课研团队,直面百亿级业务运维现场！','http://47.94.86.115:9000/java1229/banners/2025/07/18/791f809ce55d463d920058de2338da35.png','https://www.atguigu.com/sre/',4,1,'2025-06-26 15:36:37','2025-07-18 12:19:57',0),(8,'智能考试系统介绍','基于AI技术的智能考试平台，支持在线考试、智能组卷等功能','http://47.94.86.115:9000/exam-system/banners/20250724/9d1d9845-ff09-4042-8397-5a3dc2335c7f-xxlx.png','https://example.com/about',1,1,'2025-07-24 16:42:53','2025-07-24 16:42:53',0),(9,'测试轮播图','测试轮播图描述','http://47.94.86.115:9000/java1229/banners/2025/07/24/ec851da3deeb402f88190c712df3462a.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20250724%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250724T085529Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=1223dc892d2c4726df8d1ca04a1ed12446ffa40191b36e2df52a53e3ece4b6a9','http://www.baidu.com',2,1,'2025-07-24 16:55:46','2025-07-24 16:55:50',0);
/*!40000 ALTER TABLE `banners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `parent_id` bigint DEFAULT '0',
  `sort` int DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (13,'选择题',0,1,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(14,'判断题',0,2,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(15,'简答题',0,3,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(16,'Java基础',13,1,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(17,'Java进阶',13,2,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(18,'Spring框架',13,3,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(19,'数据库',13,4,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(20,'前端技术',13,5,'2025-07-23 14:17:32','2025-07-23 14:17:32',1),(21,'Java语法',14,1,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(22,'编程规范',14,2,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(23,'最佳实践',14,3,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(24,'系统设计',15,1,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(25,'性能优化',15,2,'2025-07-23 14:17:32','2025-07-23 14:17:32',0),(26,'架构设计',15,3,'2025-07-23 14:17:32','2025-07-23 14:17:32',1),(29,'maven工具',13,0,'2025-07-25 11:12:21','2025-07-25 11:12:21',0),(30,'git',13,0,'2025-07-25 11:39:43','2025-07-25 11:39:43',0),(31,'测试4',14,6,'2025-07-25 11:50:31','2025-07-25 11:52:59',0);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_records`
--

DROP TABLE IF EXISTS `exam_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_records` (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_id` int NOT NULL,
  `student_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `score` int DEFAULT '0',
  `answers` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'ONGOING',
  `window_switches` int DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_records`
--

LOCK TABLES `exam_records` WRITE;
/*!40000 ALTER TABLE `exam_records` DISABLE KEYS */;
INSERT INTO `exam_records` VALUES (38,15,'赵伟风',0,NULL,'2025-07-30 15:18:44',NULL,'进行中',0,'2025-07-30 15:18:43','2025-07-30 15:18:43',0),(39,17,'xxx',0,NULL,'2025-07-30 16:32:25',NULL,'进行中',0,'2025-07-30 16:32:25','2025-07-30 16:32:25',0),(40,20,'法国德国',0,NULL,'2025-07-30 16:47:43',NULL,'进行中',0,'2025-07-30 16:47:43','2025-07-30 16:47:42',0),(41,20,'测试',10,'本次考试成绩显示，学生在理解和掌握课程内容方面存在较大差距。得分率仅为16.7%，反映出学生对知识点的掌握不全面，解题技巧和应用能力亟待提高。优势在于能够答对两道题目，显示出一定的基础理解能力。不足之处在于多数题目未能正确解答，需要加强知识点的深入理解和运用。建议学生从基础知识入手，加强概念理解，多做练习题，提高解题速度和准确度。同时，注重培养逻辑思维和分析问题的能力。保持积极态度，相信自己能够通过努力提高成绩。加油，你有能力做得更好！','2025-07-30 18:54:51','2025-07-30 18:55:11','已批阅',0,'2025-07-30 18:54:51','2025-07-30 18:54:50',0),(42,20,'12',0,NULL,'2025-11-01 15:46:52',NULL,'进行中',0,'2025-11-01 07:46:50','2025-11-01 07:46:50',0);
/*!40000 ALTER TABLE `exam_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notices`
--

DROP TABLE IF EXISTS `notices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notices` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'NOTICE' COMMENT '公告类型：SYSTEM(系统)、FEATURE(新功能)、NOTICE(通知)',
  `priority` int DEFAULT '0' COMMENT '优先级：0-普通，1-重要，2-紧急',
  `is_active` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_type` (`type`) USING BTREE,
  KEY `idx_priority` (`priority`) USING BTREE,
  KEY `idx_is_active` (`is_active`) USING BTREE,
  KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notices`
--

LOCK TABLES `notices` WRITE;
/*!40000 ALTER TABLE `notices` DISABLE KEYS */;
INSERT INTO `notices` VALUES (4,'Updated Title','Updated Content','SYSTEM',2,1,'2025-06-25 18:27:00','2025-06-25 23:42:01',0),(5,'New AI Generation Feature','We are excited to announce the new AI question generation feature. It supports multiple question types and difficulty levels.','FEATURE',1,1,'2025-06-25 18:27:00','2025-06-25 18:27:00',1),(6,'Exam Guidelines','Please ensure stable network connection during online exams. Do not switch windows unnecessarily. Contact support if you encounter technical issues.','NOTICE',0,1,'2025-06-25 18:27:00','2025-06-25 18:27:00',0),(7,'Welcome to Online Exam System','This system provides complete online examination solutions including question management, paper generation, online testing, and automatic grading.','NOTICE',0,1,'2025-06-25 18:27:00','2025-06-25 18:27:00',0),(8,'Test Notice','This is a test notice','NOTICE',0,1,'2025-06-25 23:42:10','2025-06-25 23:42:10',0),(9,'TestNotice','Test','NOTICE',0,1,'2025-06-25 23:42:18','2025-06-25 23:42:18',1);
/*!40000 ALTER TABLE `notices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paper` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'DRAFT',
  `total_score` decimal(10,2) DEFAULT '0.00',
  `question_count` int DEFAULT '0',
  `duration` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
INSERT INTO `paper` VALUES (9,'测试1','阿达大大大','PUBLISHED',60.00,12,60,'2025-07-29 18:31:44','2025-07-30 11:22:11',0),(15,'测试ai组卷','测试ai组卷 描述！','PUBLISHED',25.00,4,60,'2025-07-30 10:52:25','2025-07-30 11:22:12',0),(16,'测试ai组卷','测试ai组卷 描述！','DRAFT',40.00,7,60,'2025-07-30 10:53:22','2025-07-30 11:22:22',1),(17,'手动2','1111','PUBLISHED',70.00,12,60,'2025-07-30 16:32:01','2025-07-30 16:32:05',0),(18,'手动333','333','DRAFT',51.00,12,20,NULL,'2025-07-30 16:43:11',0),(19,'手动44','大概打发','PUBLISHED',60.00,12,60,NULL,'2025-07-30 16:44:34',0),(20,'手动55','相对湿度防守打法','PUBLISHED',60.00,12,60,'2025-07-30 16:46:37','2025-07-30 16:46:40',0),(21,'ai组卷222','阿斯达大厦','DRAFT',79.00,12,50,'2025-07-30 16:57:34','2025-07-30 16:57:34',0);
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper_question`
--

DROP TABLE IF EXISTS `paper_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paper_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `paper_id` int NOT NULL,
  `question_id` bigint NOT NULL,
  `score` decimal(10,2) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper_question`
--

LOCK TABLES `paper_question` WRITE;
/*!40000 ALTER TABLE `paper_question` DISABLE KEYS */;
INSERT INTO `paper_question` VALUES (97,9,66,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(98,9,68,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(99,9,69,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(100,9,71,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(101,9,72,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(102,9,73,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(103,9,74,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(104,9,75,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(105,9,76,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(106,9,77,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(107,9,78,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(108,9,79,5.00,'2025-07-29 18:31:44','2025-07-29 18:31:44',0),(109,15,75,5.00,'2025-07-30 10:52:27','2025-07-30 11:15:17',1),(110,15,76,5.00,'2025-07-30 10:52:27','2025-07-30 11:15:17',1),(111,15,77,5.00,'2025-07-30 10:52:27','2025-07-30 11:15:17',1),(112,15,78,5.00,'2025-07-30 10:52:27','2025-07-30 11:15:17',1),(113,15,79,5.00,'2025-07-30 10:52:27','2025-07-30 11:15:17',1),(114,15,68,5.00,'2025-07-30 10:52:29','2025-07-30 11:15:17',1),(115,15,69,10.00,'2025-07-30 10:52:31','2025-07-30 11:15:17',1),(116,16,75,5.00,'2025-07-30 10:53:23','2025-07-30 11:22:22',1),(117,16,76,5.00,'2025-07-30 10:53:23','2025-07-30 11:22:22',1),(118,16,77,5.00,'2025-07-30 10:53:23','2025-07-30 11:22:22',1),(119,16,78,5.00,'2025-07-30 10:53:23','2025-07-30 11:22:22',1),(120,16,79,5.00,'2025-07-30 10:53:23','2025-07-30 11:22:22',1),(121,16,68,5.00,'2025-07-30 10:53:23','2025-07-30 11:22:22',1),(122,16,69,10.00,'2025-07-30 10:53:23','2025-07-30 11:22:22',1),(123,15,69,10.00,'2025-07-30 11:15:17','2025-07-30 11:15:17',0),(124,15,72,5.00,'2025-07-30 11:15:17','2025-07-30 11:15:17',0),(125,15,74,5.00,'2025-07-30 11:15:17','2025-07-30 11:15:17',0),(126,15,79,5.00,'2025-07-30 11:15:17','2025-07-30 11:15:17',0),(127,17,66,5.00,NULL,NULL,NULL),(128,17,68,5.00,NULL,NULL,NULL),(129,17,69,5.00,NULL,NULL,NULL),(130,17,71,5.00,NULL,NULL,NULL),(131,17,72,5.00,NULL,NULL,NULL),(132,17,73,9.00,NULL,NULL,NULL),(133,17,74,9.00,NULL,NULL,NULL),(134,17,75,7.00,NULL,NULL,NULL),(135,17,76,5.00,NULL,NULL,NULL),(136,17,77,5.00,NULL,NULL,NULL),(137,17,78,5.00,NULL,NULL,NULL),(138,17,79,5.00,NULL,NULL,NULL),(139,18,66,5.00,NULL,NULL,0),(140,18,68,5.00,NULL,NULL,0),(141,18,69,5.00,NULL,NULL,0),(142,18,71,5.00,NULL,NULL,0),(143,18,72,5.00,NULL,NULL,0),(144,18,73,5.00,NULL,NULL,0),(145,18,74,5.00,NULL,NULL,0),(146,18,75,5.00,NULL,NULL,0),(147,18,76,1.00,NULL,NULL,0),(148,18,77,2.00,NULL,NULL,0),(149,18,78,3.00,NULL,NULL,0),(150,18,79,5.00,NULL,NULL,0),(151,19,66,5.00,NULL,NULL,0),(152,19,68,5.00,NULL,NULL,0),(153,19,69,5.00,NULL,NULL,0),(154,19,71,5.00,NULL,NULL,0),(155,19,72,5.00,NULL,NULL,0),(156,19,73,5.00,NULL,NULL,0),(157,19,74,5.00,NULL,NULL,0),(158,19,75,5.00,NULL,NULL,0),(159,19,76,5.00,NULL,NULL,0),(160,19,77,5.00,NULL,NULL,0),(161,19,78,5.00,NULL,NULL,0),(162,19,79,5.00,NULL,NULL,0),(163,20,66,5.00,'2025-07-30 16:46:37',NULL,0),(164,20,68,5.00,'2025-07-30 16:46:37',NULL,0),(165,20,69,5.00,'2025-07-30 16:46:37',NULL,0),(166,20,71,5.00,'2025-07-30 16:46:37',NULL,0),(167,20,72,5.00,'2025-07-30 16:46:37',NULL,0),(168,20,73,5.00,'2025-07-30 16:46:37',NULL,0),(169,20,74,5.00,'2025-07-30 16:46:37',NULL,0),(170,20,75,5.00,'2025-07-30 16:46:37',NULL,0),(171,20,76,5.00,'2025-07-30 16:46:37',NULL,0),(172,20,77,5.00,'2025-07-30 16:46:37',NULL,0),(173,20,78,5.00,'2025-07-30 16:46:37',NULL,0),(174,20,79,5.00,'2025-07-30 16:46:37',NULL,0),(175,21,71,5.00,'2025-07-30 16:57:34',NULL,0),(176,21,72,5.00,'2025-07-30 16:57:34',NULL,0),(177,21,75,5.00,'2025-07-30 16:57:34',NULL,0),(178,21,76,5.00,'2025-07-30 16:57:34',NULL,0),(179,21,77,5.00,'2025-07-30 16:57:34',NULL,0),(180,21,78,5.00,'2025-07-30 16:57:34',NULL,0),(181,21,79,5.00,'2025-07-30 16:57:34',NULL,0),(182,21,66,8.00,'2025-07-30 16:57:34',NULL,0),(183,21,68,8.00,'2025-07-30 16:57:34',NULL,0),(184,21,74,8.00,'2025-07-30 16:57:34',NULL,0),(185,21,69,10.00,'2025-07-30 16:57:34',NULL,0),(186,21,73,10.00,'2025-07-30 16:57:34',NULL,0);
/*!40000 ALTER TABLE `paper_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_answers`
--

DROP TABLE IF EXISTS `question_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_answers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question_id` bigint NOT NULL,
  `answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `keywords` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_answers`
--

LOCK TABLES `question_answers` WRITE;
/*!40000 ALTER TABLE `question_answers` DISABLE KEYS */;
INSERT INTO `question_answers` VALUES (59,65,'A',NULL,'2025-07-25 17:15:17','2025-07-27 12:38:02',1),(60,66,'TRUE',NULL,'2025-07-25 17:20:05','2025-07-25 17:27:21',0),(61,67,'B,C',NULL,'2025-07-27 11:16:13','2025-07-27 11:29:36',1),(62,68,'TRUE',NULL,'2025-07-27 11:17:05','2025-07-27 11:17:05',0),(63,69,'简答题答案！',NULL,'2025-07-27 11:17:31','2025-07-27 11:17:31',0),(65,70,'A,D',NULL,'2025-07-27 12:39:06','2025-07-27 12:40:29',1),(66,71,'B,C',NULL,'2025-07-27 17:23:32','2025-07-27 17:23:32',0),(67,72,'A,C',NULL,'2025-07-27 18:28:56','2025-07-27 18:28:56',0),(68,73,'啦啦啦',NULL,'2025-07-28 17:28:42','2025-07-28 17:28:42',0),(69,74,'true',NULL,'2025-07-28 17:28:42','2025-07-28 17:28:42',0),(70,75,'A,B,C',NULL,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(71,76,'A,B,D',NULL,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(72,77,'A,B,C,D',NULL,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(73,78,'A,B,C',NULL,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(74,79,'A,B,C,D',NULL,'2025-07-29 16:51:12','2025-07-29 16:51:12',0);
/*!40000 ALTER TABLE `question_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_choices`
--

DROP TABLE IF EXISTS `question_choices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_choices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `question_id` bigint NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_correct` tinyint(1) DEFAULT '0',
  `sort` int DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_choices`
--

LOCK TABLES `question_choices` WRITE;
/*!40000 ALTER TABLE `question_choices` DISABLE KEYS */;
INSERT INTO `question_choices` VALUES (62,65,'测试1',0,0,'2025-07-25 17:14:42','2025-07-27 12:38:02',1),(63,65,'测试2',1,0,'2025-07-25 17:14:52','2025-07-27 12:38:02',1),(64,65,'判断1',0,0,'2025-07-25 17:22:08','2025-07-27 12:38:02',1),(65,65,'判断2',0,0,'2025-07-25 17:22:17','2025-07-27 12:38:02',1),(66,67,'111',0,0,'2025-07-27 11:16:13','2025-07-27 11:29:36',1),(67,67,'222',1,0,'2025-07-27 11:16:13','2025-07-27 11:29:36',1),(68,67,'333',1,0,'2025-07-27 11:16:13','2025-07-27 11:29:36',1),(69,67,'444',0,0,'2025-07-27 11:16:13','2025-07-27 11:29:36',1),(70,67,'111',1,0,'2025-07-27 11:16:13','2025-07-27 12:37:23',1),(71,67,'222',1,0,'2025-07-27 11:16:13','2025-07-27 12:37:23',1),(72,67,'333',0,0,'2025-07-27 11:16:13','2025-07-27 12:37:23',1),(73,67,'444',1,0,'2025-07-27 11:16:13','2025-07-27 12:37:23',1),(74,70,'999',1,0,'2025-07-27 12:39:06','2025-07-27 12:40:29',1),(75,70,'888',0,0,'2025-07-27 12:39:06','2025-07-27 12:40:29',1),(76,70,'777',0,0,'2025-07-27 12:39:06','2025-07-27 12:40:29',1),(77,70,'666',1,0,'2025-07-27 12:39:06','2025-07-27 12:40:29',1),(78,71,'111',0,0,'2025-07-27 17:23:32','2025-07-27 17:23:32',0),(79,71,'222',1,0,'2025-07-27 17:23:32','2025-07-27 17:23:32',0),(80,71,'333',1,0,'2025-07-27 17:23:32','2025-07-27 17:23:32',0),(81,71,'4444',0,0,'2025-07-27 17:23:32','2025-07-27 17:23:32',0),(82,72,'1',1,0,'2025-07-27 18:28:56','2025-07-27 18:28:56',0),(83,72,'2',0,0,'2025-07-27 18:28:56','2025-07-27 18:28:56',0),(84,72,'3',1,0,'2025-07-27 18:28:56','2025-07-27 18:28:56',0),(85,72,'4',0,0,'2025-07-27 18:28:56','2025-07-27 18:28:56',0),(86,75,'构造器注入',1,1,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(87,75,'字段注入',1,2,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(88,75,'Setter方法注入',1,3,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(89,75,'接口注入',0,4,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(90,76,'@Required',1,1,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(91,76,'@Autowired',1,2,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(92,76,'@Qualifier',0,3,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(93,76,'@Service',1,4,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(94,77,'execution表达式',1,1,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(95,77,'within表达式',1,2,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(96,77,'this表达式',1,3,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(97,77,'bean表达式',1,4,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(98,78,'REQUIRED',1,1,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(99,78,'SUPPORTS',1,2,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(100,78,'MANDATORY',1,3,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(101,78,'NEVER',0,4,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(102,79,'@Component',1,1,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(103,79,'@Service',1,2,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(104,79,'@Repository',1,3,'2025-07-29 16:51:12','2025-07-29 16:51:12',0),(105,79,'@Controller',1,4,'2025-07-29 16:51:12','2025-07-29 16:51:12',0);
/*!40000 ALTER TABLE `question_choices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `multi` tinyint(1) DEFAULT '0',
  `category_id` bigint NOT NULL,
  `difficulty` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `score` int DEFAULT '5',
  `analysis` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (65,'测试选择题','CHOICE',0,13,'MEDIUM',5,'数据库的数据类型','2025-07-24 18:39:22','2025-07-27 12:38:02',1),(66,'测试判断题','JUDGE',0,14,'HARD',5,'java是世界上最好的语言','2025-07-25 17:14:14','2025-07-25 17:14:14',0),(67,'选择题1222','CHOICE',1,17,'HARD',5,'选择题解析','2025-07-27 11:16:13','2025-07-27 12:37:23',1),(68,'判断题','JUDGE',0,22,'MEDIUM',5,'判断题解析！','2025-07-27 11:16:13','2025-07-27 11:17:05',0),(69,'简答题1','TEXT',0,24,'MEDIUM',5,'简答题解析！','2025-07-27 11:16:13','2025-07-27 11:17:31',0),(70,'选择题999','CHOICE',1,30,'MEDIUM',5,'89999','2025-07-27 11:16:13','2025-07-27 12:40:29',1),(73,'以下哪个是Spring框架的核心特性？','TEXT',0,1,'MEDIUM',5,'Spring框架的核心特性包括依赖注入、面向切面编程和事务管理等。','2025-07-28 17:28:42','2025-07-28 17:28:42',0),(75,'Spring框架中的依赖注入(DI)可以通过哪些方式实现？','CHOICE',1,17,'EASY',5,'Spring框架支持多种依赖注入方式，包括构造器注入、字段注入和Setter方法注入，这些都是常见的依赖注入方式。接口注入不是Spring框架中支持的依赖注入方式。','2025-07-29 16:51:12','2025-07-29 16:51:12',0),(76,'以下哪些注解用于Spring框架中声明Bean？','CHOICE',1,17,'EASY',5,'在Spring框架中，@Required、@Autowired和@Service注解都可以用来声明Bean。@Required注解用于指示必须注入一个bean，否则会抛出异常；@Autowired注解用于自动注入依赖；@Service注解用于标注服务层组件。@Qualifier注解用于指定注入的具体Bean，但本身不声明Bean。','2025-07-29 16:51:13','2025-07-29 16:51:13',0),(77,'Spring框架中的AOP（面向切面编程）支持哪些类型的切点表达式？','CHOICE',1,17,'EASY',5,'Spring框架中的AOP支持多种类型的切点表达式，包括execution表达式用于匹配方法执行，within表达式用于匹配指定类中的所有方法，this表达式用于匹配当前代理对象，以及bean表达式用于匹配特定名称的Bean。','2025-07-29 16:51:13','2025-07-29 16:51:13',0),(78,'Spring框架中的@Transactional注解默认支持哪些事务传播行为？','CHOICE',1,17,'EASY',5,'Spring框架中的@Transactional注解默认支持REQUIRED、SUPPORTS和MANDATORY三种事务传播行为。REQUIRED表示如果当前存在事务，则加入该事务；SUPPORTS表示如果当前存在事务，可以加入事务，否则以非事务方式执行；MANDATORY表示当前必须存在事务，否则抛出异常。NEVER表示当前方法不允许在事务中执行，与默认行为不同。','2025-07-29 16:51:13','2025-07-29 16:51:13',0),(79,'在Spring框架中，以下哪些注解可以用来声明组件？','CHOICE',1,17,'EASY',5,'在Spring框架中，@Component、@Service、@Repository和@Controller注解都可以用来声明组件。@Component注解用于泛指组件，@Service注解用于服务层组件，@Repository注解用于数据访问层组件，@Controller注解用于表现层组件。这些注解都可以使类成为Spring容器的Bean，从而被自动扫描和注册。','2025-07-29 16:51:13','2025-07-29 16:51:13',0);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'user',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'active',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除，1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin123','Admin','admin','active','2025-06-23 22:00:18','2025-06-23 22:00:18',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_categories`
--

DROP TABLE IF EXISTS `video_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '分类描述',
  `parent_id` bigint DEFAULT '0' COMMENT '父级分类ID，0为顶级',
  `sort_order` int DEFAULT '0' COMMENT '排序权重',
  `status` tinyint DEFAULT '1' COMMENT '状态：1-启用，0-禁用',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_parent_id` (`parent_id`) USING BTREE,
  KEY `idx_sort_order` (`sort_order`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='视频分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_categories`
--

LOCK TABLES `video_categories` WRITE;
/*!40000 ALTER TABLE `video_categories` DISABLE KEYS */;
INSERT INTO `video_categories` VALUES (1,'Java基础','Java编程语言基础知识讲解，适合初学者',0,1,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(2,'Java进阶','Java高级特性、框架应用和最佳实践',0,2,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(3,'Spring框架','Spring生态系统相关技术栈详解',0,3,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(4,'数据库','数据库设计、优化和管理相关知识',0,4,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(5,'前端技术','前端开发技术栈和工具使用',0,5,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(6,'系统设计','架构设计、系统分析和最佳实践',0,6,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(7,'面试指导','技术面试准备和常见问题解答',0,7,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(8,'项目实战','完整项目开发流程和实战经验分享',0,8,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(11,'语法基础','Java基础语法和概念',1,1,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(12,'面向对象','Java面向对象编程思想',1,2,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(13,'集合框架','Java集合类和数据结构',1,3,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(14,'异常处理','Java异常机制和处理方式',1,4,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(21,'多线程','Java并发编程和线程安全',2,1,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(22,'JVM原理','Java虚拟机内存管理和性能调优',2,2,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(23,'设计模式','常用设计模式在Java中的应用',2,3,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(24,'网络编程','Java网络编程和Socket通信',2,4,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(31,'Spring Core','Spring核心容器和IoC/DI',3,1,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(32,'Spring MVC','Spring Web MVC框架',3,2,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(33,'Spring Boot','Spring Boot快速开发',3,3,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(34,'Spring Cloud','Spring Cloud微服务架构',3,4,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(41,'MySQL','MySQL数据库管理和优化',4,1,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(42,'Redis','Redis缓存技术和应用',4,2,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(43,'MongoDB','MongoDB NoSQL数据库',4,3,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(44,'数据库设计','数据库建模和设计规范',4,4,1,'2025-06-29 19:40:04','2025-06-29 19:40:04'),(45,'嵌入式','嵌入式技术',0,8,1,'2025-06-29 22:37:42','2025-06-29 22:37:42'),(46,'嵌入式测试1','ccc',45,0,1,'2025-06-29 22:37:55','2025-06-29 22:37:55');
/*!40000 ALTER TABLE `video_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_likes`
--

DROP TABLE IF EXISTS `video_likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_likes` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
  `video_id` bigint NOT NULL COMMENT '视频ID',
  `user_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户IP（匿名点赞）',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户代理信息',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_video_ip` (`video_id`,`user_ip`) USING BTREE,
  KEY `idx_video_id` (`video_id`) USING BTREE,
  KEY `idx_created_at` (`created_at`) USING BTREE,
  CONSTRAINT `video_likes_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `videos` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='视频点赞表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_likes`
--

LOCK TABLES `video_likes` WRITE;
/*!40000 ALTER TABLE `video_likes` DISABLE KEYS */;
INSERT INTO `video_likes` VALUES (1,1,'192.168.1.100','Mozilla/5.0 (Windows NT 10.0; Win64; x64)','2025-06-29 19:40:04'),(2,1,'192.168.1.101','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)','2025-06-29 19:40:04'),(3,2,'192.168.1.102','Mozilla/5.0 (X11; Linux x86_64)','2025-06-29 19:40:04'),(4,2,'192.168.1.103','Mozilla/5.0 (Windows NT 10.0; Win64; x64)','2025-06-29 19:40:04'),(5,3,'192.168.1.104','Mozilla/5.0 (iPhone; CPU iPhone OS 14_6 like Mac OS X)','2025-06-29 19:40:04'),(6,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36','2025-06-29 23:00:50'),(7,9,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36','2025-07-09 18:03:15');
/*!40000 ALTER TABLE `video_likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_views`
--

DROP TABLE IF EXISTS `video_views`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_views` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '观看ID',
  `video_id` bigint NOT NULL COMMENT '视频ID',
  `user_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户IP',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户代理信息',
  `view_duration` int DEFAULT NULL COMMENT '观看时长（秒）',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '观看时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_video_id` (`video_id`) USING BTREE,
  KEY `idx_created_at` (`created_at`) USING BTREE,
  KEY `idx_user_ip` (`user_ip`) USING BTREE,
  CONSTRAINT `video_views_ibfk_1` FOREIGN KEY (`video_id`) REFERENCES `videos` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='视频观看记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_views`
--

LOCK TABLES `video_views` WRITE;
/*!40000 ALTER TABLE `video_views` DISABLE KEYS */;
INSERT INTO `video_views` VALUES (1,1,'192.168.1.100','Mozilla/5.0 (Windows NT 10.0; Win64; x64)',1650,'2025-06-29 19:40:04'),(2,1,'192.168.1.101','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)',1800,'2025-06-29 19:40:04'),(3,2,'192.168.1.102','Mozilla/5.0 (X11; Linux x86_64)',2100,'2025-06-29 19:40:04'),(4,2,'192.168.1.103','Mozilla/5.0 (Windows NT 10.0; Win64; x64)',2400,'2025-06-29 19:40:04'),(5,3,'192.168.1.104','Mozilla/5.0 (iPhone; CPU iPhone OS 14_6 like Mac OS X)',2800,'2025-06-29 19:40:04'),(6,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',0,'2025-06-29 23:00:41'),(7,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',3,'2025-06-29 23:00:44'),(8,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',3,'2025-06-29 23:00:45'),(10,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',9,'2025-06-29 23:00:49'),(11,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',9,'2025-06-29 23:02:16'),(12,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',21,'2025-06-29 23:02:22'),(14,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',0,'2025-06-29 23:10:53'),(15,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',6,'2025-06-29 23:10:59'),(17,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',18,'2025-06-29 23:11:10'),(19,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',127,'2025-06-29 23:12:54'),(20,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',0,'2025-06-29 23:32:07'),(21,6,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',4,'2025-06-29 23:32:12'),(22,7,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',0,'2025-06-29 23:39:46'),(23,7,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',2,'2025-06-29 23:39:48'),(24,7,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',0,'2025-07-03 18:58:50'),(25,7,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',2,'2025-07-03 18:58:53'),(27,9,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',0,'2025-07-09 18:03:04'),(28,9,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',2,'2025-07-09 18:03:07'),(29,9,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',23,'2025-07-09 18:03:29'),(30,9,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',0,'2025-07-16 14:45:11'),(31,9,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',10,'2025-07-16 14:45:22'),(32,9,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',20,'2025-07-16 14:45:22'),(33,3,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36',0,'2025-11-02 17:25:52');
/*!40000 ALTER TABLE `video_views` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '视频ID',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '视频描述',
  `category_id` bigint NOT NULL COMMENT '分类ID',
  `file_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频文件URL',
  `cover_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封面图片URL',
  `duration` int DEFAULT NULL COMMENT '视频时长（秒）',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小（字节）',
  `uploader_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上传者名称',
  `uploader_type` tinyint DEFAULT '1' COMMENT '上传者类型：1-用户投稿，2-管理员上传',
  `user_id` bigint DEFAULT NULL COMMENT '上传用户ID（用户投稿时）',
  `admin_id` bigint DEFAULT NULL COMMENT '管理员ID（管理员上传时）',
  `status` tinyint DEFAULT '0' COMMENT '状态：0-待审核，1-已发布，2-已拒绝，3-已下架',
  `audit_admin_id` bigint DEFAULT NULL COMMENT '审核管理员ID',
  `audit_time` timestamp NULL DEFAULT NULL COMMENT '审核时间',
  `audit_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '审核原因（拒绝时）',
  `view_count` bigint DEFAULT '0' COMMENT '观看次数',
  `like_count` bigint DEFAULT '0' COMMENT '点赞次数',
  `tags` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标签，逗号分隔',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_category_id` (`category_id`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_uploader_type` (`uploader_type`) USING BTREE,
  KEY `idx_view_count` (`view_count`) USING BTREE,
  KEY `idx_like_count` (`like_count`) USING BTREE,
  KEY `idx_created_at` (`created_at`) USING BTREE,
  CONSTRAINT `videos_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `video_categories` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='视频信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (1,'Java基础语法入门','从零开始学习Java编程语言，掌握基本语法和编程思想',11,'https://upos-sz-estghw.bilivideo.com/upgcxcode/00/87/387638700/387638700_nb3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&nbs=1&platform=html5&trid=3143a79e3d5841279e5de37559b5ddbh&og=hw&mid=0&oi=1782024106&gen=playurlv3&os=estghw&uipk=5&deadline=1762081920&upsig=2e462f14fc10843cc57e19f7aec31965&uparams=e','https://tse4-mm.cn.bing.net/th/id/OIP-C.-PhSDsqED95if7oFEOiIywHaEK?w=275&h=180&c=7&r=0&o=7&dpr=1.7&pid=1.7&rm=3',1800,104857600,'技术讲师',2,NULL,1,1,1,'2025-06-29 19:40:04','视频内容优质，适合初学者学习',156,23,'Java,基础,语法,入门','2025-06-29 19:40:04','2025-11-01 10:27:24'),(2,'Spring Boot快速上手','快速掌握Spring Boot核心特性和快速开发技巧',33,'https://upos-sz-estghw.bilivideo.com/upgcxcode/00/87/387638700/387638700_nb3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&nbs=1&platform=html5&trid=3143a79e3d5841279e5de37559b5ddbh&og=hw&mid=0&oi=1782024106&gen=playurlv3&os=estghw&uipk=5&deadline=1762081920&upsig=2e462f14fc10843cc57e19f7aec31965&uparams=e','https://tse4-mm.cn.bing.net/th/id/OIP-C.-PhSDsqED95if7oFEOiIywHaEK?w=275&h=180&c=7&r=0&o=7&dpr=1.7&pid=1.7&rm=3',NULL,NULL,'og',2,NULL,NULL,1,NULL,NULL,'1',234,45,'Spring Boot,快速开发,微服务','2025-06-29 19:40:04','2025-11-01 10:34:45'),(3,'MySQL性能优化实战','深入讲解MySQL查询优化和性能调优的最佳实践',41,'https://upos-sz-estghw.bilivideo.com/upgcxcode/00/87/387638700/387638700_nb3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&nbs=1&platform=html5&trid=3143a79e3d5841279e5de37559b5ddbh&og=hw&mid=0&oi=1782024106&gen=playurlv3&os=estghw&uipk=5&deadline=1762081920&upsig=2e462f14fc10843cc57e19f7aec31965&uparams=e,nbs,platform,trid,og,mid,oi,gen,os,uipk,deadline&bvc=vod&nettype=0&bw=168309&dl=0&f=h_0_0&agrr=1&buvid=&build=0&orderid=0,1','https://tse4-mm.cn.bing.net/th/id/OIP-C.-PhSDsqED95if7oFEOiIywHaEK?w=275&h=180&c=7&r=0&o=7&dpr=1.7&pid=1.7&rm=3',3600,287654321,'DBA专家',2,NULL,1,1,1,'2025-06-29 19:40:04','干货满满，实用性强',90,12,'MySQL,性能优化,数据库,DBA','2025-06-29 19:40:04','2025-11-01 10:32:35'),(4,'Vue3前端开发指南','全面介绍Vue3新特性和现代前端开发最佳实践',5,'https://upos-sz-estghw.bilivideo.com/upgcxcode/00/87/387638700/387638700_nb3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&nbs=1&platform=html5&trid=3143a79e3d5841279e5de37559b5ddbh&og=hw&mid=0&oi=1782024106&gen=playurlv3&os=estghw&uipk=5&deadline=1762081920&upsig=2e462f14fc10843cc57e19f7aec31965&uparams=e','https://tse4-mm.cn.bing.net/th/id/OIP-C.-PhSDsqED95if7oFEOiIywHaEK?w=275&h=180&c=7&r=0&o=7&dpr=1.7&pid=1.7&rm=3',NULL,NULL,'og',2,NULL,NULL,1,NULL,NULL,'1',178,34,'Vue3,前端,JavaScript,组件化','2025-06-29 19:40:04','2025-11-01 10:34:45'),(5,'微服务架构设计','深入解析微服务架构设计原理和实践经验',6,'https://upos-sz-estghw.bilivideo.com/upgcxcode/00/87/387638700/387638700_nb3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&nbs=1&platform=html5&trid=3143a79e3d5841279e5de37559b5ddbh&og=hw&mid=0&oi=1782024106&gen=playurlv3&os=estghw&uipk=5&deadline=1762081920&upsig=2e462f14fc10843cc57e19f7aec31965&uparams=e','https://tse4-mm.cn.bing.net/th/id/OIP-C.-PhSDsqED95if7oFEOiIywHaEK?w=275&h=180&c=7&r=0&o=7&dpr=1.7&pid=1.7&rm=3',NULL,NULL,'og',2,NULL,NULL,1,NULL,NULL,'1',67,8,'微服务,架构设计,分布式,系统设计','2025-06-29 19:40:04','2025-11-01 10:34:45'),(6,'客户端测试1','大叔大婶打打卡时间看哈的撒',11,'https://upos-sz-estghw.bilivideo.com/upgcxcode/00/87/387638700/387638700_nb3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&nbs=1&platform=html5&trid=3143a79e3d5841279e5de37559b5ddbh&og=hw&mid=0&oi=1782024106&gen=playurlv3&os=estghw&uipk=5&deadline=1762081920&upsig=2e462f14fc10843cc57e19f7aec31965&uparams=e','https://tse4-mm.cn.bing.net/th/id/OIP-C.-PhSDsqED95if7oFEOiIywHaEK?w=275&h=180&c=7&r=0&o=7&dpr=1.7&pid=1.7&rm=3',NULL,NULL,'og',2,NULL,NULL,1,NULL,NULL,'1',12,1,'测试,测试1','2025-06-29 22:49:13','2025-11-01 10:34:45'),(7,'好视频','湿哒哒大大大阿德撒旦撒旦撒阿达撒打算打算',12,'https://upos-sz-estghw.bilivideo.com/upgcxcode/00/87/387638700/387638700_nb3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&nbs=1&platform=html5&trid=3143a79e3d5841279e5de37559b5ddbh&og=hw&mid=0&oi=1782024106&gen=playurlv3&os=estghw&uipk=5&deadline=1762081920&upsig=2e462f14fc10843cc57e19f7aec31965&uparams=e','https://tse4-mm.cn.bing.net/th/id/OIP-C.-PhSDsqED95if7oFEOiIywHaEK?w=275&h=180&c=7&r=0&o=7&dpr=1.7&pid=1.7&rm=3',NULL,NULL,'og',2,NULL,NULL,1,NULL,NULL,'1',4,0,'xxx,x,x,x,xx','2025-06-29 23:39:17','2025-11-01 10:34:45'),(8,'测试视频','手打达到休息休息休息休息',22,'https://upos-sz-estghw.bilivideo.com/upgcxcode/00/87/387638700/387638700_nb3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&nbs=1&platform=html5&trid=3143a79e3d5841279e5de37559b5ddbh&og=hw&mid=0&oi=1782024106&gen=playurlv3&os=estghw&uipk=5&deadline=1762081920&upsig=2e462f14fc10843cc57e19f7aec31965&uparams=e','https://tse4-mm.cn.bing.net/th/id/OIP-C.-PhSDsqED95if7oFEOiIywHaEK?w=275&h=180&c=7&r=0&o=7&dpr=1.7&pid=1.7&rm=3',NULL,NULL,'og',2,NULL,NULL,1,NULL,NULL,'1',0,0,'测试,好好,大家好','2025-06-29 23:45:27','2025-11-01 10:34:45'),(9,'xxx','xxx',12,'https://upos-sz-estghw.bilivideo.com/upgcxcode/00/87/387638700/387638700_nb3-1-16.mp4?e=ig8euxZM2rNcNbRVhwdVhwdlhWdVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&nbs=1&platform=html5&trid=3143a79e3d5841279e5de37559b5ddbh&og=hw&mid=0&oi=1782024106&gen=playurlv3&os=estghw&uipk=5&deadline=1762081920&upsig=2e462f14fc10843cc57e19f7aec31965&uparams=e','https://tse4-mm.cn.bing.net/th/id/OIP-C.-PhSDsqED95if7oFEOiIywHaEK?w=275&h=180&c=7&r=0&o=7&dpr=1.7&pid=1.7&rm=3',NULL,NULL,'og',2,NULL,NULL,1,NULL,NULL,'1',6,1,'che,he','2025-07-09 18:02:23','2025-11-01 10:34:45');
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-02 18:51:14
