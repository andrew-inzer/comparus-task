CREATE TABLE users(
    user_id VARCHAR(32) NOT NULL,
    login VARCHAR(32) NOT NULL,
    first_name VARCHAR(32) NOT NULL,
    last_name VARCHAR(32) NOT NULL,
    PRIMARY KEY (user_id)
);

INSERT INTO users(user_id, login, first_name, last_name)
VALUES ('1', 'jdoe', 'John', 'Doe');