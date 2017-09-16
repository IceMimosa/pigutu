# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.18)
# Database: pigutu
# Generation Time: 2017-09-16 17:21:21 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parameter` varchar(100) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table feed_back
# ------------------------------------------------------------

DROP TABLE IF EXISTS `feed_back`;

CREATE TABLE `feed_back` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table image_set
# ------------------------------------------------------------

DROP TABLE IF EXISTS `image_set`;

CREATE TABLE `image_set` (
  `id` int(11) NOT NULL,
  `all_images_id` int(11) NOT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table image_set_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `image_set_list`;

CREATE TABLE `image_set_list` (
  `id` int(11) NOT NULL,
  `category` varchar(50) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  `cover_url` varchar(100) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `img_count` int(11) DEFAULT NULL,
  `label` varchar(50) DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `view_count` int(11) DEFAULT '0',
  `recommend_count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table like_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `like_record`;

CREATE TABLE `like_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `all_images_id` int(11) DEFAULT NULL,
  `cover_url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
