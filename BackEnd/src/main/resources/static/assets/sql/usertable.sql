-- CREATE DATABASE IF NOT EXISTS `enjoytrip`;
-- USE `enjoytrip`;
-- UNLOCK TABLES;
-- DROP TABLE IF EXISTS `noticeboard`;
-- DROP TABLE IF EXISTS `reviewboard`;
-- DROP TABLE IF EXISTS `freeboard`;
-- DROP TABLE IF EXISTS `teamboard`;
-- DROP TABLE IF EXISTS `path`;
-- DROP TABLE IF EXISTS `user`;

-- ALTER TABLE attraction_info
-- ADD COLUMN win int default 0;

create table user(
user_id varchar(100) primary key,
name varchar(100) not null,
pass varchar(100) not null,
email varchar(100) not null,
path_cnt int not null default 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `user` WRITE;
INSERT INTO `user`(user_id, name, pass, email) VALUES ('ssafy','김싸피','1234','ssafy@ssafy.com'),('admin','관리자','1234','admin@ssafy.com');
UNLOCK TABLES;

create table path(
    id int auto_increment primary key,
    user_id varchar(100),
    path_id int,
    sequence int,
    content_id int,
    constraint FOREIGN KEY (user_id) REFERENCES user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table noticeboard(
num int primary key auto_increment,
createtime datetime,
title varchar(40) ,
content varchar(100) ,
writerid varchar(100),
hits int default 0,
constraint FOREIGN KEY (writerid) REFERENCES user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table reviewboard(
num int primary key auto_increment,
createtime datetime ,
title varchar(40) ,
content varchar(100) ,
writerid varchar(100),
hits int default 0,
constraint foreign key(writerid) references user(user_id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table freeboard(
num int primary key auto_increment,
createtime datetime ,
title varchar(40) ,
content varchar(100) ,
writerid varchar(100),
hits int default 0,
constraint foreign key(writerid) references user(user_id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table teamboard(
num int primary key auto_increment,
createtime datetime ,
title varchar(40) ,
content varchar(100) ,
writerid varchar(100),
hits int default 0,
constraint foreign key(writerid) references user(user_id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- select * from user;
-- select * from noticeboard;
-- select * from reviewboard;
-- select * from freeboard;
-- select * from teamboard;