DROP SCHEMA IF EXISTS lead_crm;
CREATE SCHEMA lead_crm;
USE lead_crm;

CREATE TABLE leads(
	id BIGINT AUTO_INCREMENT NOT NULL,
	name VARCHAR(255),
    phone_number VARCHAR(255),
    email VARCHAR(255),
    company_name VARCHAR(255),
    salesrep_id BIGINT,
    PRIMARY KEY (id)
);

insert into leads(name, phone_number, email, company_name, salesrep_id) values
('Nacho', '123456789', 'nachuatadawi@hotmail.com', 'm&p', 1),
('Villa', '123456789', 'villa@hotmail.com', 'm&p', 2),
('Herrera', '123456789', 'sofa@hotmail.com', 'sofa', 2),
('Juan Alberto', '123456789', 'alfajon@gmail.com', 'medicos con fronteras' ,1);
INSERT INTO leads (name, phone_number, email, company_name, salesrep_id) VALUES
	("Pepe Pig", "000000000", "pepepig@cerdos.com", "Cerditos oINC.", 1),
	("Pepa Pig", "000000001", "pepapig@cerdos.com", "Cerditos oINC.", 2),
	("Pupa Pig", "000000002", "pupapig@cerdos.com", "Cerdotes &Co.", 1)
;
INSERT INTO leads (name, phone_number, email, company_name, salesrep_id) VALUES
	("Pepe Pig", "000000000", "pepepig@cerdos.com", "Cerditos oINC.", 1),
	("Pepa Pig", "000000001", "pepapig@cerdos.com", "Cerditos oINC.", 2),
	("Pupa Pig", "000000002", "pupapig@cerdos.com", "Cerdotes &Co.", 1)
;