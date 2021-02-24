DROP SCHEMA IF EXISTS lead_crm_test;
CREATE SCHEMA lead_crm_test;
USE lead_crm_test;

CREATE TABLE leads(
	id BIGINT AUTO_INCREMENT NOT NULL,
	name VARCHAR(255),
    phone_number VARCHAR(255),
    email VARCHAR(255),
    company_name VARCHAR(255),
    salesrep_id BIGINT,
    PRIMARY KEY (id)
);
