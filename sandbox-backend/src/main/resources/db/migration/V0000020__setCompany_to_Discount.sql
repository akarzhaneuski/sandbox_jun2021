UPDATE discount d SET companyId=3 WHERE d.id=1;
UPDATE discount d SET companyId=3 WHERE d.id=2;
UPDATE discount d SET companyId=1 WHERE d.id=3;
UPDATE discount d SET companyId=5 WHERE d.id=4;

TRUNCATE TABLE discount_tag;

INSERT INTO discount_tag (discountId, tagId)
VALUES (1,1),
       (2,1),
       (2,2),
       (2,3),
       (3,3),
       (4,4);
