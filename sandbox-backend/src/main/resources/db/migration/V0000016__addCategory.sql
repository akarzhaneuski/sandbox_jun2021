CREATE TABLE category (
    id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(50)
);

INSERT INTO category (name)
VALUES ('sport'),
       ('education'),
       ('entertainment'),
       ('food');

ALTER TABLE tag ADD COLUMN categoryId INT;
ALTER TABLE tag ADD foreign key (categoryId) references category (id);

UPDATE tag SET tagName='sushi', categoryId=4 WHERE tagName='food';
UPDATE tag SET tagName='pool', categoryId=1 WHERE tagName='sport';
UPDATE tag SET tagName='trainee', categoryId=2 WHERE tagName='education';
UPDATE tag SET categoryId=3 WHERE tagName='cinema';