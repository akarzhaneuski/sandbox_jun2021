ALTER TABLE location DROP COLUMN city;

CREATE TABLE city(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100),
    countryId   INT,
    FOREIGN KEY (countryId) REFERENCES country (id)
);

CREATE TABLE address
(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100),
    latitude   INT DEFAULT 0,
    longitude  INT DEFAULT 0,
    cityId   INT,
    FOREIGN KEY (cityId) REFERENCES city (id)
);


ALTER TABLE country ADD COLUMN locationId  INT;
ALTER TABLE country ADD FOREIGN KEY (locationId) REFERENCES location (id);

INSERT INTO city (id,name,countryId)
    VALUES (1,'Lviv',1),
           (2,'Kyiv',1),
           (3,'Minsk',2),
           (4,'Grodno',2),
           (5,'Moskow',3),
           (6,'Pinsk',2);

INSERT INTO address (id, name,cityId)
    VALUES (1,'Gagarina 32',1),
           (2,'I.Franka 2',1),
           (3,'B.Hmlenytskogo 7',2),
           (4,'Middle 322',3),
           (5,'Baker str. 221b',4),
           (6,'Champs-Élysées',5),
           (7,'Pushkina 2',6);

UPDATE country SET country.locationId=1 WHERE id=1;
UPDATE country SET country.locationId=2 WHERE id=2;
UPDATE country SET country.locationId=3 WHERE id=3;

ALTER TABLE discount ADD COLUMN locationId INT;
ALTER TABLE discount ADD FOREIGN KEY (locationId) REFERENCES location (id);

UPDATE discount SET locationId=1 WHERE id=1;
