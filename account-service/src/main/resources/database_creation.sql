DROP SCHEMA IF EXISTS account_crm;
CREATE SCHEMA account_crm;
USE account_crm;

CREATE TABLE account(
	id BIGINT AUTO_INCREMENT NOT NULL,
    industry VARCHAR(255),
    employee_count INT,
    city VARCHAR(255),
    country VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO account (industry, employee_count, city, country) VALUES
    ("PRODUCE", 20, "Helsinki", "Finland"),
    ("MEDICAL", 250, "Oviedo", "Spain"),
    ("ECOMMERCE", 17, "Albacete", "Spain"),
    ("MANUFACTURING", 30, "Chartres", "France")
;