DROP SCHEMA IF EXISTS salesrep_crm_test;
CREATE SCHEMA salesrep_crm_test;
USE salesrep_crm_test;

CREATE TABLE sales_rep(
	id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY(id)
);