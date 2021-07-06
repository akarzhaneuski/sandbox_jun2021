CREATE TABLE employee_category(
    id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    categoryId  INT NOT NULL,
    employeeId  INT NOT NULL,
    FOREIGN KEY (categoryId)        REFERENCES category (id),
    FOREIGN KEY (employeeId)        REFERENCES employee (id),
    CONSTRAINT FK_employee_category UNIQUE (categoryId, employeeId)
);

ALTER TABLE employee ADD COLUMN email VARCHAR(320);

UPDATE employee SET email='exadelovichjhon@gmail.com' WHERE id=1;
UPDATE employee SET email='E00002@pochtu.net' WHERE id=2;
UPDATE employee SET email='E00003@pochtu.net' WHERE id=3;
UPDATE employee SET email='E00004@pochtu.net' WHERE id=4;
