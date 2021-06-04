CREATE TABLE IF NOT EXISTS `DEMO_TABLE`
(

    `ID`         int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `SOME_FIELD` varchar(20)

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;
CREATE TABLE employee
(
    id          INT                        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login       VARCHAR(50)                NOT NULL,
    password    VARCHAR(255)               NOT NULL,
    role        enum ('user', 'moderator') NOT NULL,
    location_id INT,
    modified    DATETIME,
    modifiedBy  VARCHAR(50),
    CONSTRAINT FK_employee_location
        FOREIGN KEY (location_id) REFERENCES location (id),
    KEY (id)
);

CREATE TABLE company
(
    id         INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    modified   DATETIME,
    modifiedBy VARCHAR(50)
);

CREATE TABLE location
(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    city       VARCHAR(100),
    modified   DATETIME,
    modifiedBy VARCHAR(50)
);

CREATE TABLE company_location
(
    id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    company_id  INT NOT NULL,
    location_id INT NOT NULL,
    FOREIGN KEY (company_id)
        REFERENCES company (id),
    FOREIGN KEY (location_id)
        REFERENCES location (id),
    CONSTRAINT FK_company_location
        UNIQUE (company_id, location_id)
);

CREATE TABLE discount
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50)  NOT NULL,
    description VARCHAR(255) NOT NULL,
    periodStart DATETIME     NOT NULL,
    periodEnd   DATETIME     NOT NULL,
    quantity    INT DEFAULT 0,
    promocode   VARCHAR(50)  NOT NULL,
    company_id  INT,
    modified    DATETIME,
    modifiedBy  VARCHAR(50),
    foreign key (company_id) references company (id)
);

CREATE TABLE tag
(
    id      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tagName VARCHAR(50)
);

CREATE TABLE discount_tag
(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    discountId INT NOT NULL,
    tagId      INT NOT NULL,
    FOREIGN KEY (discountId) REFERENCES discount (id),
    FOREIGN KEY (tagId) REFERENCES tag (id),
    CONSTRAINT FK_discount_tag
        UNIQUE (discountId, tagId)
);

CREATE TABLE employee_discount
(
    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    employeeId INT NOT NULL,
    discountId INT NOT NULL,
    FOREIGN KEY (employeeId) REFERENCES employee (id),
    FOREIGN KEY (discountId) REFERENCES discount (id),
    CONSTRAINT FK_employee_discount
        UNIQUE (employeeId, discountId)
);

CREATE TABLE review
(
    id          INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rate        INT      NOT NULL,
    comment     VARCHAR(500),
    date        DATETIME NOT NULL,
    discount_id INT      NOT NULL,
    employee_id INT      NOT NULL,
    FOREIGN KEY (discount_id) REFERENCES discount (id),
    FOREIGN KEY (employee_id) REFERENCES employee (id)
);