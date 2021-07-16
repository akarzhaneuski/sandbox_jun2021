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

INSERT INTO company_address (companyId, addressId)
VALUES (2,1),
       (3,2);