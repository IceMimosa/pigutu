ALTER TABLE `me`.`comment`
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

ALTER TABLE `me`.`comment`
CHANGE COLUMN `image_id` `image_id` INT(15) NULL DEFAULT 0 ,
CHANGE COLUMN `from_user` `from_user` INT(11) NULL DEFAULT 0 ,
CHANGE COLUMN `to_user` `to_user` INT(11) NULL DEFAULT 0 ;
-- 评论表存用户id，省的改名字
ALTER TABLE `me`.`comment`
CHANGE COLUMN `id` `id` INT(11) NOT NULL ,
CHANGE COLUMN `from_user` `from_user` INT(11) NULL ,
CHANGE COLUMN `to_user` `to_user` INT(11) NULL DEFAULT NULL ;
-- 评论表增加likeCount
ALTER TABLE `me`.`comment`
ADD COLUMN `like_count` INT(11) NULL DEFAULT 0 AFTER `content`;
-- 新建反馈表
CREATE TABLE `me`.`feedback` (
  `id` INT ZEROFILL NOT NULL AUTO_INCREMENT,
  `content` LONGTEXT NULL,
  `contact` VARCHAR(45) NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_id` INT(15) NULL,
  `from_user` VARCHAR(45) NULL,
  `to_user` VARCHAR(45) NULL,
  `time` DATETIME NULL,
  `content` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `user` (
  `id` INT ZEROFILL NOT NULL AUTO_INCREMENT,
  `icon` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `point` INT(10) ZEROFILL NULL,
  `vip` INT(5) ZEROFILL NULL,
  PRIMARY KEY (`id`));

-- 新建权限表
CREATE TABLE `role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

-- 用户表
ALTER TABLE `user`
CHANGE COLUMN `username` `name` VARCHAR(100) NOT NULL ,
CHANGE COLUMN `password` `password` VARCHAR(100) NOT NULL ,
ADD COLUMN `point` INT(45) NULL AFTER `password`,
ADD COLUMN `vip` INT(45) NULL AFTER `point`,
ADD COLUMN `permission` VARCHAR(45) NULL AFTER `vip`,
ADD COLUMN `role` VARCHAR(45) NULL AFTER `permission`;

-- 更新表
CREATE TABLE `upgrade` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `version_name` VARCHAR(45) NULL,
  `info` VARCHAR(200) NULL,
  `apk_url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

-- 默认值改 0
ALTER TABLE `image_set_list`
CHANGE COLUMN `id` `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `comment_count` `comment_count` INT(11) ZEROFILL NULL DEFAULT NULL ,
CHANGE COLUMN `img_count` `img_count` INT(11) ZEROFILL NULL ,
CHANGE COLUMN `like_count` `like_count` INT(11) ZEROFILL NULL DEFAULT NULL ,
CHANGE COLUMN `view_count` `view_count` INT(11) ZEROFILL NULL DEFAULT '0' ,
CHANGE COLUMN `recommend_count` `recommend_count` INT(11) ZEROFILL NULL DEFAULT '0' ,
CHANGE COLUMN `hide` `hide` INT(2) ZEROFILL NULL DEFAULT '0' ;
