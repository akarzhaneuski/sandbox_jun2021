INSERT INTO discount (name, description, periodStart, periodEnd, promocode)
VALUES ('KING KEBAB', 'Good Kebab', '10.05.2021', '15.07.2021','kebab');
INSERT INTO discount (name, description, periodStart, periodEnd, promocode)
VALUES ('I <3 KEBAB', 'Best Kebab ever', '10.05.2021', '15.07.2021','kebabkebab');
INSERT INTO discount (name, description, periodStart, periodEnd, promocode)
VALUES ('Cinema', 'Free ticket', '10.05.2021', '15.07.2021','F4U');
INSERT INTO discount (name, description, periodStart, periodEnd, promocode)
VALUES ('SportLife', 'Free Month', '10.05.2021', '15.07.2021','BICEPS');

UPDATE discount SET companyId = 3 WHERE id =1;
UPDATE discount SET companyId = 3 WHERE id =2;
UPDATE discount SET companyId = 3 WHERE id =3;
UPDATE discount SET companyId = 5 WHERE id =4;