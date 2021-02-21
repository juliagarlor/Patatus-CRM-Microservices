DROP SCHEMA IF EXISTS opportunity_crm_test;
CREATE SCHEMA opportunity_crm_test;
USE opportunity_crm_test;

CREATE TABLE opportunity(
	id BIGINT AUTO_INCREMENT NOT NULL,
	quantity INT,
	decision_maker_id BIGINT,
	status VARCHAR(255),
	product VARCHAR(255),
	rep_opportunity_id BIGINT,
	account_id BIGINT,
	PRIMARY KEY(id)
);