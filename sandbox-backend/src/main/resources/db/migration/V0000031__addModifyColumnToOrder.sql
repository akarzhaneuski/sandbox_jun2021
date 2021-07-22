ALTER TABLE `order`
    ADD COLUMN modified DATETIME;
ALTER TABLE `order`
    ADD COLUMN modifiedBy VARCHAR(50);