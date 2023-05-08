create database USER_SYSTEM;
use USER_SYSTEM;

Create table user(
	USER_ID Integer primary key auto_increment,
    NAME varchar(100) not null,
    EMAIL varchar(150) not null unique,
    PASSWORD varchar(250) not null,
    ROLE varchar(100) not null
)
