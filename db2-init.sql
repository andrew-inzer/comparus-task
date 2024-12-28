CREATE TABLE user_table(
    ldap_login VARCHAR(32) NOT NULL,
    name VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    PRIMARY KEY (ldap_login)
);

INSERT INTO user_table(ldap_login, name, surname)
VALUES ('example-user-id-2', 'Testuser', 'Testov');