package com.exadel.sandbox.team5.util;

import com.exadel.sandbox.team5.entity.Discount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class SubscriptionManager {

    private Map<Long, String> newDiscounts;
    private Instant lastExecution;

    public void addDiscount(Discount discount) {
        newDiscounts.put(discount.getCategory().getId(), discount.getName());
    }

    public void clear() {
        newDiscounts.clear();
        lastExecution = Instant.now();
    }
}
