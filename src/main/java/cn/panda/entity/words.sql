create table words
(
  id INT(10) AUTO_INCREMENT PRIMARY KEY,
  word VARCHAR (100) UNIQUE not null,
  times INT(10)
)