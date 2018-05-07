CREATE TABLE `me`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_id` INT(15) NULL,
  `from_user` VARCHAR(45) NULL,
  `to_user` VARCHAR(45) NULL,
  `time` DATETIME NULL,
  `content` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `me`.`user` (
  `id` INT ZEROFILL NOT NULL AUTO_INCREMENT,
  `icon` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `point` INT(10) ZEROFILL NULL,
  `vip` INT(5) ZEROFILL NULL,
  PRIMARY KEY (`id`));

-- 新建权限表
CREATE TABLE `me`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

-- 用户表
ALTER TABLE `me`.`user`
CHANGE COLUMN `username` `name` VARCHAR(100) NOT NULL ,
CHANGE COLUMN `password` `password` VARCHAR(100) NOT NULL ,
ADD COLUMN `point` INT(45) NULL AFTER `password`,
ADD COLUMN `vip` INT(45) NULL AFTER `point`,
ADD COLUMN `permission` VARCHAR(45) NULL AFTER `vip`,
ADD COLUMN `role` VARCHAR(45) NULL AFTER `permission`;

-- 更新表
CREATE TABLE `me`.`upgrade` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `version_name` VARCHAR(45) NULL,
  `info` VARCHAR(200) NULL,
  `apk_url` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

