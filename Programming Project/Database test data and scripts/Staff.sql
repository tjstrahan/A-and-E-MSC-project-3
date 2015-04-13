Create Table Staff(

StaffID int(6) PRIMARY KEY,

Role varchar(20) not null,

Title varchar(10) not null,

First_Name varchar(30) not null,

Middle_Name varchar(20),

Last_Name varchar(30) not null,

DOB date not null,

Sex char(1) not null,

First_line_of_address varchar(30) not null,

Second_line_of_address varchar(30),

Third_line_of_address varchar(30),

City varchar(30) not null,

Postcode varchar(8) not null,

Contact_number bigint(12)not null,

Mobile_number bigint(12)not null);


INSERT INTO Staff VALUES("700300", "Doctor", "Dr", "John", null, "Allen", "19991-12-28", "M", "12 

Rose Close", null, null, "Belfast", "BT1 5PN", "447777658080", "447777658080");

INSERT INTO Staff VALUES ("700301", "Nurse", "Mrs", "Pam", null, "Gordon", "1971-01-01", "F", "10 

Rose Street", null, null, "Belfast", "BT2 4PN", "447370668282", "447370668282" );

INSERT INTO Staff VALUES ("700302", "Doctor", "Dr", "Colin", null, "Watts", "1970-03-03", "M", "8 

Rose Gardens", null, null, "Belfast", "BT3 1MO", "447677788013", "447677788013" );

INSERT INTO Staff VALUES ("700303", "Doctor", "Dr", "Kieron", null, "Allsop", "1972-04-02", "M", "22 

Lavender Close", null, null, "Belfast", "BT5 3ER", "447557648697", "447557648697" );

INSERT INTO Staff VALUES ("700304", "Doctor", "Dr", "Naveed", null, "Agahi", "1967-10-10", "M", 

"28 Heather Drive", null, null, "Belfast", "BT6 6YT", "447752040070", "447752040070");

INSERT INTO Staff VALUES ("700305", "Doctor", "Dr", "Emma", null, "Scroggie", "1962-12-12", "F", "1 

Heather Road", null, null, "Belfast", "BT7 8PO", "447922673599", "447922673599" );

INSERT INTO Staff VALUES ("700306", "Doctor", "Dr", "Tom", null, "McDonnell", "1975-08-01", "M", 

"28 Lavender Street", null, null, "Belfast", "BT8 5TY", "447594010105", "447594010105");

INSERT INTO Staff VALUES ("700307", "Doctor", "Dr", "James", null, "Maguire", "1983-09-03", "M", 

"16 Rose Park", null, null, "Belfast", "BT9 4ED", "0447845896938", "0447845896938"); 

INSERT INTO Staff VALUES ("700308", "Doctor", "Dr", "Janine", null, "Kelly", "1980-06-11", "F", "108 

Daffodil Street", null, null, "Belfast", "BT10 7UY", "0447724927494","0447724927494" ); 

INSERT INTO Staff VALUES ("700309", "Doctor", "Dr", "James", null, "Strahan", "1965-11-11", "M", 

"119 Daffodil Gardens", null, null, "Belfast", "BT11 2UQ", "447914219569", "447914219569");

INSERT INTO Staff VALUES ("700310", "Nurse", "Miss", "Nuala", null, "O'Neill", "1960-12-12", "F", 

"122 Bluebell Close", null, null, "Belfast", "BT12 7YF", "447876757023", "447876757023" ); 

INSERT INTO Staff VALUES ("700311", "Receptionist", "Mr", "David", null, "Keeler", "1977-12-07", "M", "8 

Bluebell Gardens", null, null, "Belfast", "BT13 8HG", "447912121213", "447912121213" );

INSERT INTO Staff VALUES ("700312", "Nurse", "Mrs", "Angela", null, "Tyson", "1988-01-13", "F", "14 

Daisy Street", null, null, "Belfast", "BT14 9HY", "0447760646321", "0447760646321"); 

INSERT INTO Staff VALUES ("700313", "Doctor", "Dr", "Mavis", null, "Andrews", "1979-08-08", "F", 

"87 Daisy Park", null, null, "Belfast", "BT15 1SD", "447886747538", "447886747538");

INSERT INTO Staff VALUES ("700314", "Doctor", "Dr", "Robert", null, "Ranson", "1966-04-13", "M", 

"23 Weed Gardens", null, null, "Belfast", "BT16 8HV", "447886754321", "447886754321" );

INSERT INTO Staff VALUES ("700315", "Nurse", "Mrs", "Davina", null, "Healey", "1976-08-22", "F", 

"37 Parry Park", null, null, "Belfast", "BT17 3ER", "447979775684", "447979775684" );

INSERT INTO Staff VALUES ("700316", "Doctor", "Dr", "Frank", null, "Jameson", "1963-05-14", "M", 

"24 Rose Avenue", null, null, "Belfast", "BT18 8KL", "447889980675", "447889980675");

INSERT INTO Staff VALUES ("700317", "Nurse", "Ms", "Philipa", null, "Crane", "1971-01-01", "F", "99 

Brick Street", null, null, "Belfast", "BT19 5TY", "447977655441", "447977655441");

INSERT INTO Staff VALUES ("700318", "Doctor", "Dr", "Sinead", null, "Fitzpatrick", "1964-03-29", "F", 

"108 Brick Street", null, null, "Belfast", "BT20 9UD", "447766544321", "447766544321");

INSERT INTO Staff VALUES ("700319", "Receptionist", "Mrs", "Denise", null, "Toner", "1980-07-05", "F", "44 

Stone Road", null, null, "Belfast", "BT21 6YR", "447988123456", "447988123456");