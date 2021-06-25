rename
table employee_discount to `order`;

alter table `order`
    add column employeePromocode VARCHAR(255) NOT NULL;
alter table `order`
    add column promoCodeStatus BOOLEAN NOT NULL;
alter table `order`
    add column promoCodePeriodStart DATETIME NOT NULL;
alter table `order`
    add column promoCodePeriodEnd DATETIME NOT NULL;