CREATE TABLE `me`.`x_user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_nickname` VARCHAR(45) NULL,
  `head_pic` VARCHAR(45) NULL,
  `count_follow` INT NULL DEFAULT 0,
  `count_fans` INT NULL DEFAULT 0,
  `count_post` INT NULL DEFAULT 0,
  `level_point` INT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`));


CREATE TABLE `me`.`x_post` (
  `post_id` INT NOT NULL AUTO_INCREMENT,
  `title` TEXT NULL,
  `user_id` INT NULL DEFAULT 0,
  `publish_time` DATETIME NULL,
  `status` INT(2) NULL DEFAULT 0,
  `count_read` INT NULL DEFAULT 0,
  `count_like` INT NULL DEFAULT 0,
  `count_reply` INT NULL DEFAULT 0,
  `count_forward` INT NULL DEFAULT 0,
  `content` MEDIUMTEXT NULL,
  `last_update_time` DATETIME NULL,
  `image_list` TEXT NULL,
  PRIMARY KEY (`post_id`));

CREATE TABLE `me`.`x_comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `status` INT(2) NULL DEFAULT 0,
  `content` MEDIUMTEXT NULL,
  `image_list` TEXT NULL,
  `user_id` INT NULL DEFAULT 0,
  `publish_time` DATETIME NULL,
  `count_like` INT NULL DEFAULT 0,
  `count_reply` INT NULL DEFAULT 0,
  `is_starter` INT(2) NULL DEFAULT 0,
  `parent_user_id` INT NULL DEFAULT 0,
  `parent_id` INT NULL DEFAULT 0,
  `is_official` INT(2) NULL DEFAULT 0,
  `is_top` INT(2) NULL DEFAULT 0,
  PRIMARY KEY (`comment_id`));
