create database if not exists musicdb;
use musicdb;

#用户表
create table user(
	uid  int primary key auto_increment,
    uname varchar(30) not null,
    stid int,
    sid int
);

#歌单表
create table songtable(
	stid int primary key auto_increment,
    stname varchar(50) not null,
    stintro nvarchar(300) not null,
    stcontent  varchar(300) not null,
    uid int
);

#歌曲表
create table song(
	sid int primary key auto_increment,
    sname varchar(50) not null,
    stid int,
    spicurl varchar(300) not null
    
);

#每日推荐表
create table everyday(
	eid int primary key auto_increment,
    ename varchar(50) not null,
    edate datetime default now(),
    stid int
);




