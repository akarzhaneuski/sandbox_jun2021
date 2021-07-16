package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.OrderDto;

import java.util.Map;

public interface OrderService extends CRUDService<OrderDto> {

    void invalidatePromoCode(String uuid);

    String createOrder(String discountId);

    Map<String, String> getOrdersByDiscounts();

    Map<String, String> getOrdersByCompanies();

    Map<String, String> getOrdersByTags();

    Map<String, String> getOrdersByCategories();
}
