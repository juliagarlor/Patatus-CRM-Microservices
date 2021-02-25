DROP SCHEMA IF EXISTS salesrep_crm;
CREATE SCHEMA salesrep_crm;
USE salesrep_crm;

CREATE TABLE sales_rep(
	id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY(id)
);

INSERT INTO sales_rep (name) VALUES
	('Aliany'),
	('Carolina'),
	('Rubén'),
	('Julia'),
	('Antonio')
;