--DROP TABLE user IF EXISTS;

CREATE TABLE user (
  username VARCHAR(30) PRIMARY KEY,
  password VARCHAR(50)
);
