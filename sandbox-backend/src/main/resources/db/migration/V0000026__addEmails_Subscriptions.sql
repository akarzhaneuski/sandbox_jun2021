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

INSERT INTO employee_category (categoryId, employeeId)
    VALUES (1,1),
           (2,1),
           (1,2),
           (1,3),
           (4,4);

CREATE TABLE params(
    id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    value VARCHAR(300)
);

INSERT INTO params (name, value) VALUE ('lastExecution', '2021-05-21 00:00:00');

UPDATE discount SET periodStart='2021-07-08 11:00:00' WHERE id=1;
UPDATE discount SET periodStart='2021-07-08 12:00:00' WHERE id=2;
UPDATE discount SET periodStart='2021-07-08 13:00:00' WHERE id=3;
UPDATE discount SET periodStart='2021-07-08 14:00:00' WHERE id=4;

INSERT INTO discount (name, description, periodStart, periodEnd, promocode, categoryId)
VALUES ('testmail1', 'testtesttest', '2021-07-08 12:00:00', '2021-08-20 21:00:00','test', 1),
       ('testmail2', 'testtesttest', '2021-07-08 12:00:00', '2021-08-20 21:00:00','test', 1),
       ('testmail3', 'testtesttest', '2021-07-08 12:00:00', '2021-08-20 21:00:00','test', 1),
       ('testmail4', 'testtesttest', '2021-07-08 12:00:00', '2021-08-20 21:00:00','test', 1),
       ('testmail5', 'testtesttest', '2021-07-08 12:00:00', '2021-08-20 21:00:00','test', 1);
