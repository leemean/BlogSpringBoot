USE blog;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `idx_account` (`account`),
    INDEX `idx_email` (`email`),
    INDEX `idx_create_time` (`create_time`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI COMMENT='用户表';

CREATE TABLE `article` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `title` varchar(256) NOT NULL,
    `author_id` bigint(20) NOT NULL,
    `body_id` bigint(20) NOT NULL,
    `category_id` int(11) DEFAULT NULL,
    `summary` varchar(128) DEFAULT NULL,
    `top` tinyint(1) NOT NULL,
    `comment_counts` int(11) DEFAULT NULL,
	`view_counts` int(11) DEFAULT NULL,
    `create_time` datetime DEFAULT NULL,
	  PRIMARY KEY(`id`),
      INDEX `idx_author_id` (`author_id`),
      INDEX `idx_body_id` (`body_id`),
      INDEX `idx_ccategory_id` (`category_id`),
      INDEX `idx_create_time` (`create_time`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI COMMENT='文章表';

CREATE TABLE `article_tag` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `tag_name` varchar(64) NOT NULL,
	  PRIMARY KEY(`id`),
      INDEX `idx_tag_name` (`tag_name`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI COMMENT='文章标签表';

CREATE TABLE `article_body` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `content` longtext NOT NULL,
    `content_html` longtext NOT NULL,
	  PRIMARY KEY(`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI COMMENT='文章标签表';

CREATE TABLE `category` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `category_name` longtext NOT NULL,
    `description` longtext NOT NULL,
	  PRIMARY KEY(`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI COMMENT='文章分类目录表';

CREATE TABLE `comment` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `content` varchar(255) DEFAULT NULL,
    `article_id` int(11) DEFAULT NULL,
    `author_id` bigint(20) DEFAULT NULL,
    `parent_id` int(11) DEFAULT NULL,
    `to_uid` bigint(20) DEFAULT NULL,
    `level` varchar(1) DEFAULT NULL,
    `create_date` datetime DEFAULT NULL,
	  PRIMARY KEY(`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI COMMENT='文章评论表';



INSERT INTO `user`(`account`,`password`,avatar,email,nickname,mobile,salt,create_time,last_login,`status`,`admin`,deleted) VALUES ('001','123456','/user/admin.png','leemeany@qq.com','liming','13711111111',
'e4153a582cbc45c3a199998b506dab28','2020-03-12 22:27:00',null,'normal',1,0)