DROP SCHEMA IF EXISTS account_crm_test;
CREATE SCHEMA account_crm_test;
USE account_crm_test;

CREATE TABLE account(
	id BIGINT AUTO_INCREMENT NOT NULL,
    industry VARCHAR(255),
    employee_count INT,
    city VARCHAR(255),
    country VARCHAR(255),
    PRIMARY KEY (id)
);