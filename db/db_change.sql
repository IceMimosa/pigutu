CREATE TABLE `me`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_id` INT(15) NULL,
  `from_user` VARCHAR(45) NULL,
  `to_user` VARCHAR(45) NULL,
  `time` DATETIME NULL,
  `content` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`));