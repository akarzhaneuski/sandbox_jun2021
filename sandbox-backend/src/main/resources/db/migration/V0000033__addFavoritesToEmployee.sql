CREATE TABLE employee_discount(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    discountId INT NOT NULL,
    employeeId INT NOT NULL,
    FOREIGN KEY (discountId) REFERENCES discount (id),
    FOREIGN KEY (employeeId) REFERENCES employee (id)
);

INSERT INTO employee_discount (employeeId, discountId)
VALUES (1,1),
       (1,2),
       (1,3);