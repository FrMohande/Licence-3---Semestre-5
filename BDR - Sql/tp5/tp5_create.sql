drop tabledro if exists certifications;
drop table if exists vols;
drop table if exists avions;
drop table if exists employes;

CREATE TABLE avions(
aid int primary key,
anom varchar(30),
portee int);

INSERT INTO avions VALUES(1,'Boeing 747-400',8430);
INSERT INTO avions VALUES(2,'Boeing 737-800',3383);
INSERT INTO avions VALUES(3,'Airbus A340-300',7120);
INSERT INTO avions VALUES(4,'British Aerospace Jetstream 41',1502);
INSERT INTO avions VALUES(5,'Embraer ERJ-145',1530);
INSERT INTO avions VALUES(6,'SAAB 340',2128);
INSERT INTO avions VALUES(7,'Piper Archer III',520);
INSERT INTO avions VALUES(8,'Tupolev 154',4103);
INSERT INTO avions VALUES(9,'Lockheed L1011',6900);
INSERT INTO avions VALUES(10,'Boeing 757-300',4010);
INSERT INTO avions VALUES(11,'Boeing 777-300',6441);
INSERT INTO avions VALUES(12,'Boeing 767-400ER',6475);
INSERT INTO avions VALUES(13,'Airbus A320',2605);
INSERT INTO avions VALUES(14,'Airbus A319',1805);
INSERT INTO avions VALUES(15,'Boeing 727',1504);
INSERT INTO avions VALUES(16,'Airbus A380-800ER',18001);
INSERT INTO avions VALUES(17,'Schwitzer 2-33',30);
