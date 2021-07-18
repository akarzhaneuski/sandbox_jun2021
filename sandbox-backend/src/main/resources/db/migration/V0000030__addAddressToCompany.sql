DROP TABLE company_country;

CREATE TABLE company_address(
        id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        companyId  INT NOT NULL,
        addressId INT,
        FOREIGN KEY (companyId)
           REFERENCES company (id),
        FOREIGN KEY (addressId)
           REFERENCES address (id)
);

UPDATE address SET address = 'Pekarska Street, 16' WHERE (id = '1');
UPDATE address SET address = 'Kopernyka Street, 20' WHERE (id = '2');
UPDATE address SET address = 'Instytutska Street, 14' WHERE (id = '3');
UPDATE address SET address = 'prasp. Niezaliežnasci 116' WHERE (id = '4');
UPDATE address SET address = 'Mostowa 31' WHERE (id = '5');
UPDATE address SET address = 'Sofiyskaya Embankment, 30' WHERE (id = '6');
UPDATE address SET address = 'Ulitsa Zoi Kosmodemyanskoy 31' WHERE (id = '7');
INSERT INTO address (address, cityId) VALUES ('Mykhaila Hrushevskoho St, 22', '2');
INSERT INTO address (address, cityId) VALUES ('vulica Maksima Bahdanoviča 23', '3');
INSERT INTO address (address, cityId) VALUES ('vulica Lievanabiarežnaja 23', '4');
INSERT INTO address (address, cityId) VALUES ('Ulitsa Okhotnyy Ryad, 12', '5');
INSERT INTO address (address, cityId) VALUES ('Ulitsa Lenina 28', '6');
INSERT INTO address (address, cityId) VALUES ('street. Serbian, 3', '1');
INSERT INTO address (address, cityId) VALUES ('Naberezhne Hwy, 25', '2');
INSERT INTO address (address, cityId) VALUES ('vulica Stachanaŭskaja 2', '3');

INSERT INTO company_address (companyId, addressId)
VALUES (2,1), (3,2), (3,1), (4,6), (5,3), (6,4), (7,5), (9,7), (10,8), (1,9);
