SET GLOBAL time_zone='+09:00';
SET time_zone='+09:00';

use mysql;
create user 'root'@'%' identified by '1234';
grant all privileges on *.* to 'root'@'%';
flush privileges;

use btree_project;
DROP table IF EXISTS boardcomment;
DROP table IF EXISTS salespost;
DROP table IF EXISTS saleslike;
DROP table IF EXISTS boardpost;
DROP table IF EXISTS boardcomment;

create table boardcomment
(
    boardcommentid bigint auto_increment
        primary key,
    boardpostid    int          null,
    commenttime    datetime(6)  null,
    content        varchar(255) null,
    username       varchar(255) null
);

create table salespost
(
    salespostId int auto_increment
        primary key,
    title       varchar(255)  null,
    content     varchar(5000)         null,
    salesimg    varchar(255) null,
    price       int        not  null default 0,
    nickname    varchar(50)  null,
    useremail   varchar(50)  null,
    category    varchar(50)  null,
    locate      varchar(100) null,
    updatetime  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    likenum     int          null,
    chatnum     int          null,
    ispoststate varchar(255) null,
    viewcount   int     not null   default 0
);

create table saleslike
(
    saleslikeid bigint auto_increment
        primary key,
    postid      bigint       null,
    username    varchar(255) null
);

create table boardpost
(
    boardpostid bigint auto_increment
        primary key,
    commentnum  int default 0 null,
    content     varchar(5000)  null,
    contentimg  varchar(255)  null,
    likenum     int default 0 null,
    locate      varchar(255)  null,
    title       varchar(255)  null,
    updatetime  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    useremail    varchar(255)  null,
    nickname    varchar(255) null,
    viewcount   int     not null   default 0
);

create table boardlike
(
    boardlikeid bigint auto_increment
        primary key,
    postid      bigint       null,
    username    varchar(255) null
);

ALTER DATABASE btree_project CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
ALTER TABLE salespost CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE boardpost CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


