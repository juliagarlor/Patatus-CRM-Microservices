drop schema opportunity_crm;
create schema opportunity_crm;
use opportunity_crm;

create table opportunity(
id bigint auto_increment,
quantity int,
decision_maker_id bigint,
status varchar(255),
product varchar(255),
rep_opportunity_id bigint,
account_id bigint,
primary key(id)
);

insert into opportunity(quantity, decision_maker_id, status, product, rep_opportunity_id, account_id) values 
(5, 2, 'OPEN', 'HYBRID', 1, 2),
(4, 3, 'CLOSED-WON', 'HYBRID', 2, 1),
(7, 1, 'CLOSED-LOST', 'OTHER', 3, 3);


select * from opportunity;