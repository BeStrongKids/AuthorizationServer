CREATE TABLE users (
   seq           BIGINT NOT NULL AUTO_INCREMENT,
   name          VARCHAR(10) NOT NULL,
   email         VARCHAR(50) NOT NULL,
   passwd        VARCHAR(80) NOT NULL,
   authorities   VARCHAR(80) NOT NULL,
   login_count   INT NOT NULL DEFAULT 0,
   last_login_at DATETIME DEFAULT NULL,
   create_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (seq),
   UNIQUE KEY unq_user_email (email)
);

# CREATE TABLE USERS(
#     id INT AUTO_INCREMENT PRIMARY KEY ,
#     name VARCHAR(20) NOT NULL,
#     password VARCHAR(500) NOT NULL,
#     email VARCHAR(40) NOT NULL,
#     phone VARCHAR(40) ,
#     enable BOOLEAN NOT NULL
# );

# create table authorities(
#     name varchar(20) not null,
#     authority varchar(50) not null,
#
# );