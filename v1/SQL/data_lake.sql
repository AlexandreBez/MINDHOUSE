create database data_lake;
use data_lake;

Create table authentication_and_security_users(
	USER_ID Integer primary key auto_increment,
    NAME varchar(100) not null,
    EMAIL varchar(150) not null unique,
    PASSWORD varchar(250) not null,
    ROLE varchar(100) not null,
    CREATION_DATE char(19) not null
)
