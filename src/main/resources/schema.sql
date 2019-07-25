DROP TABLE IF EXISTS stack;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS GLOBAL_SEQ;

CREATE SEQUENCE GLOBAL_SEQ START WITH 1;

CREATE TABLE users
(
  id   INT PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX users_name_idx ON users (name);

CREATE TABLE stack
(
  id      INT PRIMARY KEY DEFAULT nextval('global_seq'),
  number  INT NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);





