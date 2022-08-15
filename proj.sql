create database proj;
use proj;
create table admin(id int primary key auto_increment,name varchar(30),mail_id varchar(30),phn_no varchar(10));

select * from admin;			-- primary key auto increment

desc  admin;

insert into admin(name,mail_id,phn_no ) values("admin1","admin1@gmail.com",'9876543211');
insert into admin(name,mail_id,phn_no ) values("admin2","admin2@gmail.com",'9876543212');

alter table admin add pass varchar(20);
update admin set pass="admin1" where id=1;
update admin set pass="admin2" where id=2;

select * from admin where mail_id="admin1@gmail.com" and phn_no="9876543211"; 

drop table admin;
truncate admin;

select * from admin where name="admin1" and pass="admin1";

		-- *********************************************************************

create table staff(id int primary key auto_increment,name varchar(30),mail_id varchar(30),phn_no varchar(10));

insert into staff(name,mail_id,phn_no ) values("staff1","staff1@gmail.com",'9876543221');
insert into staff(name,mail_id,phn_no ) values("staff2","staff2@gmail.com",'9876543222');
insert into staff(name,mail_id,phn_no ) values("staff3","staff3@gmail.com",'9876543223');

alter table staff add pass varchar(20);
update staff set pass="staff1" where id=1;
update staff set pass="staff2" where id=2;
update staff set pass="staff3" where id=3;

select * from staff;

delete from staff where id=6;

drop table staff;
truncate staff;
		-- **********************************************************************

create table customer(id int primary key auto_increment,name varchar(30),mail_id varchar(30),phn_no varchar(10));

insert into customer(name,mail_id,phn_no ) values("cus1","cus1@gmail.com",'9876543231');
insert into customer(name,mail_id,phn_no ) values("cus2","cus2@gmail.com",'9876543232');
insert into customer(name,mail_id,phn_no ) values("cus3","cus3@gmail.com",'9876543233');
insert into customer(name,mail_id,phn_no ) values("cus4","cus4@gmail.com",'9876543234');
insert into customer(name,mail_id,phn_no ) values("cus5","cus5@gmail.com",'9876543235');

alter table customer add pass varchar(20);
update customer set pass="cus1" where id=1;
update customer set pass="cus2" where id=2;
update customer set pass="cus3" where id=3;
update customer set pass="cus4" where id=4;
update customer set pass="cus5" where id=5;

insert into customer values("dev","dev@123","9876543210","dev43");  -- wrong

delete from customer where id=6;
select * from customer;

drop table customer;
truncate customer;

		-- **************************************************************************

