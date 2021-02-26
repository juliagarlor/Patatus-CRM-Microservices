drop schema contact_crm_test;
create schema contact_crm_test;
use contact_crm_test;


create table contact(
id int auto_increment,
name varchar(255),
phone_number varchar(255),
email varchar(255),
company_name varchar(255),
account_id bigint,
primary key(id)
);

insert into contact (name, phone_number, email, company_name, account_id) values 
('Juan Alberto', '665734987', 'jherrador@gmail.com', 'médicos con fronteras', 1),
('Nacho', '647123123', 'nachuatadawi@hotmail.com', 'magic&phoenix',2),
('Brian', 666666666, 'brianElJudio@hotmail.com', 'oro and Samir friend', 3);
