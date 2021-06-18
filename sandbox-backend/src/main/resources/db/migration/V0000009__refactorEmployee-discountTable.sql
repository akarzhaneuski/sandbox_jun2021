rename
table employee_discount to bookingList;

alter table bookingList
    add column employeePromocode VARCHAR(255) NOT NULL;
alter table bookingList
    add column promoCodeStatus BOOLEAN NOT NULL;
alter table bookingList
    add column promoCodePeriodStart DATETIME NOT NULL;
alter table bookingList
    add column promoCodePeriodEnd DATETIME NOT NULL;