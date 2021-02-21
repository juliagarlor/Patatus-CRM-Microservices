drop schema salesrep_crm;
create schema salesrep_crm;
use salesrep_crm;

create table sales_rep(
	id bigint auto_increment,
    name varchar(255),
    primary key(id)
);

insert into sales_rep (name) values 
('Aliani'),
('Carolina'),
('Ruben'),
('Julia'),
('Antonio');

select * from sales_rep;