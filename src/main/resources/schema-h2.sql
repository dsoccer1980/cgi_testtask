DROP TABLE IF EXISTS stack;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  id   INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX users_name_idx ON users (name);

CREATE TABLE stack
(
  id      INT PRIMARY KEY AUTO_INCREMENT,
  number  INT NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);





