CREATE TABLE discount_address(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    discountId  INT NOT NULL,
    addressId INT,
    FOREIGN KEY (discountId)
        REFERENCES discount (id),
    FOREIGN KEY (addressId)
        REFERENCES address (id),
    CONSTRAINT FK_discount_address
        UNIQUE (discountId, addressId)
);

ALTER TABLE discount ADD COLUMN addressId INT;
ALTER TABLE discount ADD CONSTRAINT FK_discount_address FOREIGN KEY (addressId) REFERENCES address (id);

UPDATE discount SET addressId=1 WHERE id=1;
UPDATE discount SET addressId=2 WHERE id=2;
UPDATE discount SET addressId=1 WHERE id=3;
UPDATE discount SET addressId=2 WHERE id=4;

INSERT INTO discount_address (discountId, addressId)
VALUES (1,1),
       (2,2),
       (3,1),
       (4,3);
