-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: app_database_ginseng
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `expiration_otp` bigint DEFAULT NULL,
  `key_otp` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'tamlom2002@gmail.com',NULL,NULL,'Nguyen Minh Tam',NULL,'$2a$10$2.Lv7roiqR90IICLeS9lQ.4r9zy8UwZ6RDld5lS0FYAJvU77Lc0vO','Super_Admin',NULL);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `dated` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `namecerti` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ginseng_wine`
--

DROP TABLE IF EXISTS `ginseng_wine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ginseng_wine` (
  `ginseng_id` int DEFAULT NULL,
  `wine_id` int NOT NULL,
  PRIMARY KEY (`wine_id`),
  KEY `FKfh08j8m5dyp3n1r9f62o9s2uy` (`ginseng_id`),
  CONSTRAINT `FK82dgek46uyyemal6txkx902u4` FOREIGN KEY (`wine_id`) REFERENCES `wines` (`id_wine`),
  CONSTRAINT `FKfh08j8m5dyp3n1r9f62o9s2uy` FOREIGN KEY (`ginseng_id`) REFERENCES `ginsengs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ginseng_wine`
--

LOCK TABLES `ginseng_wine` WRITE;
/*!40000 ALTER TABLE `ginseng_wine` DISABLE KEYS */;
/*!40000 ALTER TABLE `ginseng_wine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ginsengs`
--

DROP TABLE IF EXISTS `ginsengs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ginsengs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `certificate` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `effect` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `more_info` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ginsengs`
--

LOCK TABLES `ginsengs` WRITE;
/*!40000 ALTER TABLE `ginsengs` DISABLE KEYS */;
INSERT INTO `ginsengs` VALUES (1,'https://studenthcmusedu-my.sharepoint.com/:wb:/g/personal/20200333_student_hcmus_edu_vn/EciVkxK_8iNIj-KwWk0Yj1MBS6g3FsMJYhMOaub9We5wog?e=hoNa2J','NS0011','2002-02-20 07:00:00.000000',NULL,'/assets/image/GinSeng/4.16.png;/assets/image/GinSeng/4.10.png;',NULL,'Nhân sâm Việt Nam');
/*!40000 ALTER TABLE `ginsengs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newspapers`
--

DROP TABLE IF EXISTS `newspapers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `newspapers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hide` bit(1) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newspapers`
--

LOCK TABLES `newspapers` WRITE;
/*!40000 ALTER TABLE `newspapers` DISABLE KEYS */;
/*!40000 ALTER TABLE `newspapers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wines`
--

DROP TABLE IF EXISTS `wines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wines` (
  `id_wine` int NOT NULL AUTO_INCREMENT,
  `codewine` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `imagewine1` varchar(255) DEFAULT NULL,
  `imagewine2` varchar(255) DEFAULT NULL,
  `imagewine3` varchar(255) DEFAULT NULL,
  `imagewine4` varchar(255) DEFAULT NULL,
  `imagewine5` varchar(255) DEFAULT NULL,
  `is_hidden` bit(1) NOT NULL,
  `namewine` varchar(255) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `qr` varchar(255) DEFAULT NULL,
  `volumewine` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_wine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wines`
--

LOCK TABLES `wines` WRITE;
/*!40000 ALTER TABLE `wines` DISABLE KEYS */;
/*!40000 ALTER TABLE `wines` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-13 11:00:21
