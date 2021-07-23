CREATE TABLE employee_favorites_discount(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    discountId INT NOT NULL,
    employeeId INT NOT NULL,
    FOREIGN KEY (discountId) REFERENCES discount (id),
    FOREIGN KEY (employeeId) REFERENCES employee (id)
);