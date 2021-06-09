SELECT * FROM vehicle_registration WHERE driver_licence_id = 'DONAL958270C99ZJ 12';
DROP TABLE IF EXISTS `anpr_camera` CASCADE;
CREATE TABLE anpr_camera(
anpr_id BIGINT PRIMARY KEY,
street_name VARCHAR(125), 
latitude double,
longitude double
);

DROP TABLE IF EXISTS `vehicle_observations` CASCADE;
CREATE TABLE vehicle_observations(
`id` BIGINT AUTO_INCREMENT PRIMARY KEY,
anpr_point_id BIGINT,
`timestamp` DateTime,
`vehicle_registration_number` VARCHAR(120)
);

DROP TABLE IF EXISTS `vehicle_registration` CASCADE;
CREATE TABLE vehicle_registration(
registration_id BIGINT PRIMARY KEY, 
registration_date DATE, 
vehicle_registration_no VARCHAR(120), 
make VARCHAR(120), 
model VARCHAR(120), 
colour VARCHAR(120), 
forenames VARCHAR(120), 
surname VARCHAR(120), 
address VARCHAR(120), 
date_of_birth DATE, 
driver_licence_id VARCHAR(120)
);

DROP TABLE IF EXISTS `vehicles` CASCADE;
CREATE TABLE vehicles(
vehicle_registration_no VARCHAR(120),
make VARCHAR(120),
model VARCHAR(120),
colour VARCHAR(120)
);

DROP TABLE IF EXISTS `atm_transaction` CASCADE;
CREATE TABLE atm_transaction(
timestamp datetime,
atm_id bigint(20),
bank_card_number bigint(20),
type varchar(45),
amount double,
id bigint(20) AUTO_INCREMENT PRIMARY KEY
);

DROP TABLE IF EXISTS `atmpoint` CASCADE;
CREATE TABLE atmpoint(
atm_id bigint(20) PRIMARY KEY AUTO_INCREMENT,
operator varchar(45),
street_name varchar(45),
postcode varchar(45),
latitude double,
longitude double
);

DROP TABLE IF EXISTS `bank_card` CASCADE;
CREATE TABLE bank_card(
bank_card_id bigint(20) PRIMARY KEY AUTO_INCREMENT,
card_number bigint(20),
sort_code varchar(255),
bank_account_id bigint(20),
account_number bigint(20),
bank varchar(225)
);

DROP TABLE IF EXISTS `people_bank_account` CASCADE;
CREATE TABLE people_bank_account(
bank_account_id bigint(20) PRIMARY KEY,
account_number bigint(20),
bank varchar(225),
forenames varchar(225),
surname varchar(225),
date_of_birth varchar(225),
home_address varchar(225)
);

DROP TABLE IF EXISTS `epos_terminal` CASCADE;
CREATE TABLE epos_terminal(
id bigint(20) PRIMARY KEY,
vendor varchar(45),
street_name varchar(45),
postcode varchar(45),
latitude varchar(45),
longitude varchar(45)
);

DROP TABLE IF EXISTS `epos_transactions` CASCADE;
CREATE TABLE epos_transactions(
timestamp datetime,
epos_id bigint(20),
bank_card_number bigint(20),
payee_account bigint(20),
amount double,
id bigint(20) PRIMARY KEY AUTO_INCREMENT
);






