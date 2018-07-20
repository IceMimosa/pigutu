-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: me
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `x_post`
--

DROP TABLE IF EXISTS `x_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `x_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT '0',
  `publish_time` datetime DEFAULT NULL,
  `status` int(2) DEFAULT '0',
  `count_read` int(11) DEFAULT '0',
  `count_like` int(11) DEFAULT '0',
  `count_reply` int(11) DEFAULT '0',
  `count_forward` int(11) DEFAULT '0',
  `content` mediumtext,
  `last_update_time` datetime DEFAULT NULL,
  `image_list` text,
  `bar_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `x_post`
--

LOCK TABLES `x_post` WRITE;
/*!40000 ALTER TABLE `x_post` DISABLE KEYS */;
INSERT INTO `x_post` VALUES (1,0,'2018-07-19 00:00:00',0,0,0,0,0,'','2018-07-19 00:00:00','aa',0),(2,0,'2018-07-19 00:00:00',0,0,0,0,0,'','2018-07-19 00:00:00','aa',0),(3,0,'2018-07-19 00:00:00',0,0,0,0,0,'','2018-07-19 00:00:00','aa',0),(4,0,'2018-07-19 00:00:00',0,0,0,0,0,'','2018-07-19 00:00:00','[\"[\\\"aa\\\"\",\"\\\"bb\\\"]\"]',0);
/*!40000 ALTER TABLE `x_post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-20 17:59:14
