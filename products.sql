use proj;

create table fruits(ID int PRIMARY KEY auto_increment,Items varchar(30), Price int, m_date date,
							exp_date date,quantity int,Tax float);

insert into fruits(Items,Price,M_date,Exp_date,quantity,Tax)values
("Apple",80,"2022-04-24","2022-05-30",25,2),
("Grapes",50,"2022-05-24","2022-05-29",25,5),
("Strawberry",40,"2022-05-24","2022-06-01",25,5),
("Orange",35,"2022-05-24","2022-06-28",25,5),
("pomegranate",40,"2022-05-20","2022-05-30",1,5);

update fruits set exp_date="2023-05-01" where id=3; 
select * from fruits where day(exp_date)>day(m_date)+5;


update fruits set quantity=25 where id=1;


select * from fruits where exp_date=curdate()-1;
-- current_date();
select curdate();
select * from fruits;
drop table fruits;

			-- ************************************

create table graints(ID int PRIMARY KEY auto_increment, Items varchar(30), Price int, M_date date,
							Exp_date date,quantity int,Tax float);

insert into graints(Items,Price,M_date,Exp_date,quantity,Tax)values
("Wheat",80,"2022-05-24","2023-05-27",30,2),
("Millet",100,"2022-05-24","2022-10-29",30,5),
("Brown_rice",70,"2022-05-24","2023-06-01",30,5),
("Dhal",150,"2022-05-24","2022-08-28",30,5),
("Barley",40,"2022-05-20","2023-05-30",30,5);  


select * from graints;

-- drop table graints;

			-- ***********************************************
            
create table cosmetics(ID int PRIMARY KEY auto_increment, Items varchar(30), Price int, M_date date,
							Exp_date date,quantity int,Tax float);

insert into cosmetics(Items,Price,M_date,Exp_date,quantity,Tax)values
("Whitening_Cream",300,"2022-05-03","2022-09-03",25,15),
("Blush",200,"2022-05-24","2022-09-09",25,5),
("Concealer",150,"2022-05-27","2022-09-09",2,10),
("Hair_Spray",400,"2022-05-28","2022-10-03",25,5),
("Foundation",290,"2022-05-20","2022-05-05",25,5);

update desserts set exp_date="2023-05-01" where id=4; 

select * from cosmetics where quantity<=5;

select * from cosmetics;

 drop table cosmetics;

			-- ****************************************************
            
create table cleaning(ID int PRIMARY KEY auto_increment, Items varchar(30), Price int, M_date date,
							Exp_date date,quantity int,Tax float);
                            
insert into cleaning(Items,Price,M_date,Exp_date,quantity,Tax)values
("Dish_wash",400,"2022-05-26","2022-09-27",20,2),
("Hand_wash",250,"2022-05-29","2022-09-23",20,5),
("Detergent_Liquid",300,"2022-05-30","2022-11-29",20,7),
("Toilet_Cleaner",500,"2022-05-30","2022-08-30",20,5),
("Floor_Cleaner",400,"2022-05-28","2022-08-08",20,5);

update cleaning set exp_date="2023-05-01" where id=1; 

select * from cleaning;

-- drop table cleaning;

			-- **********************************************
            
create table desserts(ID int PRIMARY KEY auto_increment, Items varchar(30), Price int, M_date date, 
							Exp_date date,quantity int,Tax float);
                            
insert into desserts(Items,Price,M_date,Exp_date,quantity,Tax)values
("Eggless_Cake",400,"2022-04-24","2022-04-28",15,2),
("Suferfree_Cake",300,"2022-05-24","2022-05-29",15,5),
("Strawberry_Cake",450,"2022-05-24","2022-06-01",15,5),
("Wheat_bread",100,"2022-05-24","2022-05-28",1,5),
("Icecream_Cake",600,"2022-05-20","2022-05-20",15,5);

update desserts set exp_date="2023-05-01" where id=4; 

select * from desserts;

drop table desserts;

	-- **********************************************

create table basket(ID int PRIMARY KEY auto_increment,catagory varchar(30), Items varchar(30), Price int, quantity int,M_date date, 
							Exp_date date,Tax float);
drop table basket;
truncate basket;

select *,(price*quantity) as Total from basket;

select * from basket;


