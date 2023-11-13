-- liquibase formatted sql

-- changeset lolipis:1
CREATE SEQUENCE account_numbers_auto_increment INCREMENT 1 START 100000;

CREATE TABLE account
(
    account_number BIGINT PRIMARY KEY DEFAULT nextval('account_numbers_auto_increment'),
    recipient_name VARCHAR(255)                 NOT NULL,
    pin_code       VARCHAR(255)                 NOT NULL,
    balance        DECIMAL(10, 2)     DEFAULT 0 NOT NULL
);

CREATE TABLE transaction
(
    id             BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    account_number BIGINT REFERENCES account (account_number),
    operation_type VARCHAR(10)    NOT NULL,
    amount         DECIMAL(10, 2) NOT NULL,
    created_date   TIMESTAMP      NOT NULL
)
