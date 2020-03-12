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

INSERT INTO `user`(`account`,`password`,avatar,email,nickname,mobile,salt,create_time,last_login,`status`,`admin`,deleted) VALUES ('001','123456','/user/admin.png','leemeany@qq.com','liming','13711111111',
'e4153a582cbc45c3a199998b506dab28','2020-03-12 22:27:00',null,'normal',1,0)