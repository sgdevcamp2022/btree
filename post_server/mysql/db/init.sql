use mysql;
create user 'root'@'%' identified by '1234';
grant all privileges on *.* to 'root'@'%';
flush privileges;

use btree_project;

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
    title       varchar(50)  null,
    content     text         null,
    salesimg    varchar(255) null,
    price       int          null,
    username    varchar(50)  null,
    category    varchar(50)  null,
    locate      varchar(100) null,
    updatetime  datetime     null,
    likenum     int          null,
    chatnum     int          null,
    ispoststate varchar(255) null
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
    content     varchar(255)  null,
    contentimg  varchar(255)  null,
    likenum     int default 0 null,
    locate      varchar(255)  null,
    title       varchar(255)  null,
    updatetime  datetime(6)   null,
    username    varchar(255)  null
);

create table boardlike
(
    boardlikeid bigint auto_increment
        primary key,
    postid      bigint       null,
    username    varchar(255) null
);

create table boardcomment
(
    boardcommentid bigint auto_increment
        primary key,
    boardpostid    int          null,
    commenttime    datetime(6)  null,
    content        varchar(255) null,
    username       varchar(255) null
);

ALTER DATABASE btree_project CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
ALTER TABLE salespost CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE boardpost CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


