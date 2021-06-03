CREATE TABLE IF NOT EXISTS `DEMO_TABLE`
(

    `ID`         int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `SOME_FIELD` varchar(20)

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;
CREATE TABLE employee(
                         id INT NOT NULL AUTO_INCREMENT,
                         login VARCHAR(50) NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         role enum ('user', 'moderator') NOT NULL,
                         location VARCHAR(100),
                         KEY  (id),
                         primary key (id));

CREATE TABLE company(
                        id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        location VARCHAR(255) NOT NULL
);

CREATE TABLE location(
                         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         city VARCHAR(50)
);

CREATE TABLE company_location (
                                  company_id INT NOT NULL,
                                  FOREIGN KEY (company_id)
                                      REFERENCES company (id),
                                  location_id INT NOT NULL,
                                  FOREIGN KEY (location_id)
                                      REFERENCES location (id)
);

CREATE TABLE discount(
                         id INT NOT NULL AUTO_INCREMENT,
                         name VARCHAR(50) NOT NULL,
                         description VARCHAR(255) NOT NULL,
                         tag VARCHAR(50) NOT NULL,
                         time DATETIME NOT NULL,
                         quantity INT DEFAULT 0,
                         promocode VARCHAR(10) NOT NULL,
                         company_id INT,
                         primary key (id),
                         foreign key (company_id) references company(id));

CREATE TABLE employee_discount(
                                  employee_id INT NOT NULL,
                                  foreign key (employee_id) references employee(id),
                                  discount_id INT NOT NULL,
                                  foreign key (discount_id) references discount(id)
);

CREATE TABLE review(
                       id INT NOT NULL AUTO_INCREMENT ,
                       rate INT NOT NULL,
                       comment VARCHAR(255),
                       date DATETIME NOT NULL,
                       discount_id INT NOT NULL,
                       employee_id INT NOT NULL,
                       PRIMARY KEY (id),
                       FOREIGN KEY (discount_id) REFERENCES discount(id),
                       FOREIGN KEY (employee_id) REFERENCES employee(id)
);