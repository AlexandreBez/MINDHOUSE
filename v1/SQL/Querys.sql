ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
flush privileges;

create database STUDENT_SYSTEM;
use STUDENT_SYSTEM;

create table STUDENT (
	STUDENT_ID integer primary key auto_increment,
    STUDENT_NAME varchar(100) not null,
	STUDENT_DOCUMENT varchar(15) unique not null,
    STUDENT_AGE varchar(2) not null,
    STUDENT_BIRTHDATE varchar(10) not null,
    STUDENT_COUNTRY varchar(30) not null,
    STUDENT_STATE varchar(20) not null,
    STUDENT_CITY varchar(50) not null,
    STUDENT_ADRESS varchar(100) not null,
    STUDENT_ZIPCODE VARCHAR(20) not null,
    STUDENT_EMAIL varchar(50) unique not null,
    STUDENT_PHONE varchar(25),
    STUDENT_PHONE_2 varchar(25),
	STUDENT_CONTRACT_TERMS boolean not null,
    STUDENT_CREATION_DATE varchar(10) not null
);

create table TEACHER(
	TEACHER_ID integer primary key auto_increment,
    TEACHER_NAME varchar(100) not null,
    TEACHER_DOCUMENT varchar(30) unique not null,
	TEACHER_AGE varchar(2) not null,
    TEACHER_BIRTHDATE varchar(10) not null,
    TEACHER_COUNTRY varchar(30) not null,
    TEACHER_STATE varchar(20) not null,
    TEACHER_CITY varchar(50) not null,
    TEACHER_ADRESS varchar(100) not null,
    TEACHER_ZIPCODE VARCHAR(20) not null,
    TEACHER_EMAIL varchar(50) unique not null,
    TEACHER_PHONE varchar(25) not null,
    TEACHER_PHONE_2 varchar(25),
    TEACHER_CREATION_DATE varchar(10) not null
);

create table COURSE(
	COURSE_ID integer primary key auto_increment,
    COURSE_NAME varchar(60) not null,
    COURSE_FIELD varchar(30) not null,
    COURSE_PRICE decimal(6,2) not null,
	COURSE_MODALITY varchar(15) not null,
    COURSE_CREATION_DATE varchar(10) not null
);

create table PROMOTION(
	PROMOTION_ID integer primary key auto_increment,
    PROMOTION_NAME varchar(100) not null,
    PROMOTION_DISCOUNT_AMOUNT decimal(4,2) not null,
    PROMOTION_DESCRIPTION  varchar(250) not null,
    PROMOTION_CREATION_DATE varchar(10) not null
);

create table PAYMENT(
	PAYMENT_ID integer primary key auto_increment,
    FK_STUDENT integer not null,
    FK_COURSE integer not null,
    FK_PROMOTION integer,
    PAYMENT_TOTAL_AMOUNT decimal(6,2) not null,
    PAYMENT_METHOD varchar(50) not null,
    PAYMENT_STATUS varchar(20) not null,
    PAYMENT_CREATION_DATE varchar(10) not null,
	foreign key (FK_STUDENT) references STUDENT(STUDENT_ID),
	foreign key (FK_COURSE) references COURSE(COURSE_ID),
    foreign key (FK_PROMOTION) references PROMOTION(PROMOTION_ID)
);

create table CLASSROOM(
	CLASSROOM_ID integer primary key auto_increment,
    FK_TEACHER integer not null,
    FK_COURSE integer not null,
	CLASSROOM_PERIOD varchar(20) not null,
    CLASSROOM_START_TIME varchar(10) not null,
    CLASSROOM_END_TIME varchar(10) not null,
    CLASSROOM_START_DATE varchar(10) not null,
    CLASSROOM_END_DATE varchar(10),
    CLASSROOM_LIMIT integer not null,
    CLASSROOM_QTD_STUDENTS integer not null,
    CLASSROOM_CREATION_DATE varchar(10) not null,
    foreign key (FK_TEACHER) references TEACHER(TEACHER_ID),
	foreign key (FK_COURSE) references COURSE(COURSE_ID)
);

create table GRADE(
	GRADE_ID integer primary key auto_increment,
    FK_CLASSROOM integer not null,
	FK_STUDENT integer not null,
	FIRST_NOTE decimal(4,2),
    SECOND_NOTE decimal(4,2),
    THIRD_NOTE decimal(4,2),
    ADDITIONAL_NOTE decimal(4,2),
    FINAL_NOTE decimal(4,2),
    STATUS varchar(15) not null,
    GRADE_CREATION_DATE varchar(10) not null,
	foreign key (FK_STUDENT) references STUDENT(STUDENT_ID),
	foreign key (FK_CLASSROOM) references CLASSROOM(CLASSROOM_ID)
);

create table STUDENT_CLASSROOM_COURSE(
	STUDENT_CLASSROOM_COURSE_ID integer primary key auto_increment,
    FK_STUDENT integer not null,
    FK_CLASSROOM integer not null,
    FK_COURSE integer not null,
	foreign key (FK_STUDENT) references STUDENT(STUDENT_ID),
	foreign key (FK_CLASSROOM) references CLASSROOM(CLASSROOM_ID),
    foreign key (FK_COURSE) references COURSE(COURSE_ID)
);