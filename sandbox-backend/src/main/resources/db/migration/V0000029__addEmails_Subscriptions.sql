CREATE TABLE employee_notification_category(
    id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    categoryId  INT NOT NULL,
    employeeId  INT NOT NULL,
    FOREIGN KEY (categoryId)        REFERENCES category (id),
    FOREIGN KEY (employeeId)        REFERENCES employee (id),
    CONSTRAINT FK_employee_category UNIQUE (categoryId, employeeId)
);

ALTER TABLE employee ADD COLUMN email VARCHAR(320);

ALTER TABLE discount ADD COLUMN isNew BOOLEAN DEFAULT 1;

UPDATE employee SET email='exadelovichjhon@gmail.com' WHERE login='E00001';
UPDATE employee SET email='E00002@pochtu.net' WHERE login='E00002';
UPDATE employee SET email='E00003@pochtu.net' WHERE login='E00003';
UPDATE employee SET email='E00004@pochtu.net' WHERE login='E00004';
UPDATE employee SET email='M00001@pochtu.net' WHERE login='M00001';
UPDATE employee SET email='admin@pochtu.net' WHERE login='admin';

INSERT INTO employee_notification_category (categoryId, employeeId)
    VALUES (1,1),
           (2,1),
           (1,2),
           (1,3),
           (3,3),
           (4,4);