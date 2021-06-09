INSERT INTO vehicles (vehicle_registration_no, make, model, colour)
VALUES ('AI51 EYW', 'Ford', 'Focus', 'white');


INSERT INTO vehicle_registration(registration_id, registration_date, vehicle_registration_no, make, model, colour, forenames, surname, address, date_of_birth, driver_licence_id)
VALUES (133411, 
'1996-10-17', 
'AI51 EYW', 
'Ford', 'Focus', 
'white', 'Christine', 
'Donald', '17 SEDGLEY PARK ROAD, MANCHESTER, M25 0BJ', 
'1990-08-27', 'DONAL958270C99ZJ 12'
);
INSERT INTO anpr_camera(anpr_id, street_name, latitude, longitude)
VALUES
 ('5538', 'Camden Road, A503', '51.54978729523243', '-0.12798197853524934'),
 ('3796',	 'M65',	'53.71601366362006',	'-2.570011337921277');

INSERT INTO vehicle_observations(anpr_point_id, `timestamp` , vehicle_registration_number)
VALUES
(3796,	'2015-05-01 09:08:52',	'AI51 EYW'),
(5538, '2015-05-01 09:08:51', 'AI51 EYW')
;
 
 INSERT INTO epos_terminal(id, vendor, street_name, postcode, latitude, longitude)
 VALUES('49178', 'Pizza Express', 'New Bury Road', 'M25 9NX', '51.0', '-0.12');
 
 INSERT INTO epos_transactions(timestamp, epos_id, bank_card_number, payee_account, amount, id)
 VALUES('2015-05-01 09:10:00', '49178', '2944149858882718', '26996565', '40', '1');
 

 INSERT INTO people_bank_account(bank_account_id, account_number, bank, 
 forenames, surname, date_of_birth, home_address)
 VALUES('997706', '97687664', 'Nationwide Building Society', 'Ryan', 'Forsyth', '1946-01-29', '53 RIPON HALL AVENUE, BURY, BL0 9RE');
 
 INSERT INTO bank_card(bank_card_id, card_number, sort_code, bank_account_id, account_number, bank)
 VALUES('914021', '2944149858882718', '54-54-98', '997706', '26149565', 'Barclays Bank');
 
 INSERT INTO atmpoint(atm_id, operator, street_name, postcode, latitude, longitude)
 VALUES('1748', 'Nationwide Building Society', 'Camden Road, A503', 'A40' , '51.54978729523243', '-0.12798197853524934');
 
 INSERT INTO atm_transaction(timestamp, atm_id, bank_card_number, type, amount, id)
 VALUES('2015-05-01 09:10:00', '1748', '2944149858882718', 'Cash Withdrawal', '40', '1');
 
 
 