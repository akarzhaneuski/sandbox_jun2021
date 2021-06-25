package com.exadel.sandbox.team5.util;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderCriteria {
    private long discountId;
    private int maxOrderSize;
    private long amountDiscountDays;
}
