create database daxueshi;
use daxueshi;
create table universities(
	university_id int,
	university_name varchar(50),
	primary key(university_id)
)engine=innodb default charset=utf8;
create table majors(
	major_id int,
	major_name varchar(50),
	primary key (major_id)
)engine=innodb default charset=utf8;
create table uni_major(
	major_id int, 
	university_id int,
	FOREIGN KEY (major_id) REFERENCES majors(major_id),
	FOREIGN KEY (university_id) REFERENCES universities(university_id)
)engine=innodb default charset=utf8;
create table companies(
	company_id int,
	company_name varchar(50),
	city varchar(20),
	type varchar(20),
	primary key(company_id)
)engine=innodb default charset=utf8;
create table users(
	user_id varchar(20),
	nickname varchar(20),
	phone_number varchar(20),
	email varchar(50),
	password varchar(20),
	portrait_url varchar(100),
	status int,
	register_time date,
	last_edit_time date,
	primary key(user_id)
)engine=innodb default charset=utf8;
create table students(
	user_id varchar(20),
	university_id int,
	major_id int,
	scores int,
	foreign key(user_id) references users(user_id),
	foreign key(university_id) references universities(university_id),
	foreign key(major_id) references majors(major_id)
)engine=innodb default charset=utf8;
create table graduates(
	user_id varchar(20),
	university_id int,
	major_id int,
	company_id int,
	score int,
	salary double,
	position varchar(20),
	foreign key(user_id) references users(user_id),
	foreign key(university_id) references universities(university_id),
	foreign key(major_id) references majors(major_id),
	foreign key(company_id) references companies(company_id)
)engine=innodb default charset=utf8;
