CREATE TABLE subtags (
    id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(50),
    tagId INT,
    foreign key (tagId) references tag (id)
);

