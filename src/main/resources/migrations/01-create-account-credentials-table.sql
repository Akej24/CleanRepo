--liquibase formatted sql
--changeset aleks:1

CREATE TABLE account (
    account_id INT AUTO_INCREMENT NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    encoded_password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    locked BOOLEAN NOT NULL,
    PRIMARY KEY(account_id)
);