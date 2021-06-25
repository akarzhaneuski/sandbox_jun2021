package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.OrderDto;
import com.exadel.sandbox.team5.util.OrderCriteria;

import java.util.List;

public interface OrderService extends CRUDService<OrderDto> {
    OrderDto invalidatePromoCode(Long discountId, String promoCode);

    OrderDto createOrder(OrderCriteria criteria);

    List<List<OrderDto>> getOrdersByIds(List<Long> discountIds);
}
