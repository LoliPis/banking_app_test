-- liquibase formatted sql

-- changeset lolipis:1
CREATE TABLE account
(
    id             BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    account_number VARCHAR(255)   NOT NULL,
    recipient_name VARCHAR(255)   NOT NULL,
    pin_code       INTEGER        NOT NULL,
    balance        DECIMAL(10, 2) NOT NULL
);

CREATE TABLE transaction
(
    id             BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    account_id     BIGINT REFERENCES account (id),
    operation_type VARCHAR(10)    NOT NULL,
    amount         DECIMAL(10, 2) NOT NULL,
    timestamp      TIMESTAMP      NOT NULL
)
