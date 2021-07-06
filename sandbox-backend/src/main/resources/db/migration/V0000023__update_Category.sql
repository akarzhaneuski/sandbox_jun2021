ALTER TABLE discount ADD COLUMN categoryId INT;
ALTER TABLE discount ADD FOREIGN KEY (categoryId) REFERENCES category (id);

UPDATE discount d SET categoryId=1 WHERE d.id=1;
UPDATE discount d SET categoryId=2 WHERE d.id=2;
UPDATE discount d SET categoryId=3 WHERE d.id=3;
UPDATE discount d SET categoryId=4 WHERE d.id=4;