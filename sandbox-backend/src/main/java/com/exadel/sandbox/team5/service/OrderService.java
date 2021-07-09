package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.OrderDto;
import com.exadel.sandbox.team5.util.CreateOrder;

import java.io.ByteArrayInputStream;
import java.util.Map;

public interface OrderService extends CRUDService<OrderDto> {
    OrderDto invalidatePromoCode(Long discountId, String promoCode);

    OrderDto createOrder(CreateOrder createOrder);

    Map<String, String> getOrdersByDiscounts();

    Map<String, String> getOrdersByCompanies();

    Map<String, String> getOrdersByTags();

    Map<String, String> getOrdersByCategories();

    ByteArrayInputStream getStatisticCSVFileOrdersByDiscounts();

    ByteArrayInputStream getStatisticCSVFileOrdersByCategories();

}
