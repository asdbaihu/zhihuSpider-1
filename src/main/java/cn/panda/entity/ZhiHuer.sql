create table zhihuer
(
  id INT(10) AUTO_INCREMENT PRIMARY KEY,
  userName VARCHAR (100),
  userId VARCHAR (100) UNIQUE not NULL ,
  slogan VARCHAR (100),
  following int(100),
  followee int(100),
  agree int(100),
  collect VARCHAR (100),
  updateTime datetime

);