use mysql;
create user 'root'@'%' identified by '1234';
grant all privileges on *.* to 'root'@'%';
flush privileges;

use btree_project;

DROP TABLE IF EXISTS es_table;
CREATE TABLE es_table (
  id BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_id (id),
  client_name VARCHAR(32) NOT NULL,
  modification_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  insertion_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP table IF EXISTS boardpost;
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
    updatetime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    username    varchar(255)  null
);
