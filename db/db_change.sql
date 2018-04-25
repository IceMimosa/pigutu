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
