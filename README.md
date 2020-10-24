## 블로그 프로젝트

### 데이터베이스 테이블 스키마

```sql
create user 'cos'@'%' identified by 'bitc5600';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database cos;
```

```sql
CREATE TABLE user(
	id int primary key auto_increment,
    username varchar(100) not null unique,
    password varchar(100) not null,
    email varchar(100) not null,
    address varchar(100) not null,
    createDate timestamp
) engine=InnoDB default charset=utf8;

CREATE TABLE post(
	id int primary key auto_increment,
    userId int,
    title varchar(100) not null,
    content longtext,
    readCount int,
    createDate timestamp,
    foreign key (userId) references user (id)
) engine=InnoDB default charset=utf8;
```
