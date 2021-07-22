package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.OrderDto;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;

import java.util.Map;

public interface OrderService extends CRUDService<OrderDto> {

    void invalidatePromoCode(String uuid);

    String createOrder(String discountId);

    Map<String, String> getOrdersByDiscounts();

    Map<String, String> getOrdersByCompanies();

    Map<String, String> getOrdersByTags();

    Map<String, String> getOrdersByCategories();

    ResultPage<OrderDto> getAll(SearchCriteria searchCriteria);
}
