DROP SCHEMA IF EXISTS lead_crm;
CREATE SCHEMA lead_crm;
USE lead_crm;

CREATE TABLE `lead`(
	id BIGINT AUTO_INCREMENT NOT NULL,
	name VARCHAR(255),
    phone_number VARCHAR(255),
    email VARCHAR(255),
    company_name VARCHAR(255),
    salesrep_id BIGINT,
    PRIMARY KEY (id)
);
INSERT INTO `lead` (name, phone_number, email, company_name, salesrep_id) VALUES
	("Pepe Pig", "000000000", "pepepig@cerdos.com", "Cerditos oINC.", 1),
	("Pepa Pig", "000000001", "pepapig@cerdos.com", "Cerditos oINC.", 2),
	("Pupa Pig", "000000002", "pupapig@cerdos.com", "Cerdotes &Co.", 1)
;