CREATE TABLE subtag (
    id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(50),
    tagId INT,
    foreign key (tagId) references tag (id)
);

INSERT INTO subtag (id, name, tagId)
    VALUES (1,'pizza',3),
           (2,'sushi',3),
           (3,'kebab',3),
           (4,'burger',3),
           (5,'yoga',1),
           (6,'pool',1),
           (7,'library',2),
           (8,'trainee',2);
