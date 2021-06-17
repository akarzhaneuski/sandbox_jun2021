package com.exadel.sandbox.team5.service.Validation;

import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.entity.Orders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@Component
public class InvalidateOrder {

    private final OrderDAO orderDAO;

    public Orders invalidatePromoCode(Long discountId, String promoCode) {

        Orders selectedOrder = orderDAO.getOrdersByDiscountIdAndEmployeePromocode(discountId, promoCode);

        if (selectedOrder != null && selectedOrder.getPromoCodePeriodEnd().getTime() > new Date().getTime()) {
            orderDAO.setPromoCodeStatus(false, promoCode);
            return selectedOrder;
        }

        return null;
    }
}
