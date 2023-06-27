create database authentication_and_security;
use authentication_and_security;

Create table users(
	USER_ID Integer primary key auto_increment,
    NAME varchar(100) not null,
    EMAIL varchar(150) not null unique,
    PASSWORD varchar(250) not null,
    ROLE varchar(100) not null,
    CREATION_DATE char(19) not null,
    NUMBER_TOKEN_RESET_PWD CHAR(6),
    EXPIRATION_DATE varchar(25)
);
