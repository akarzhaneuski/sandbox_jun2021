package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.OrderDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    DiscountDAO discountDAO;

    @Mock
    CompanyDAO companyDAO;

    @Mock
    OrderDAO orderDAO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInvalidatePromoCode() throws NoSuchElementException {
        //orderService.invalidatePromoCode(2l, "a452ec28-2c91-40e7-a973-373450ea5a92");
        //assertEquals(OrderDto.class, orderService.invalidatePromoCode(2l, "8bec6f63-4d49-45eb-a5e4-57a3614b872f"));

    }

    @Test
    void getOrdersByDiscountsTest() {
        Map<String, String> testMap = orderService.getOrdersByDiscounts();
        assertNotNull(testMap);
        assertEquals(new HashMap().getClass(), testMap.getClass());
    }

    @Test
    void getOrdersByCompaniesTest() {
        Map<String, String> testMap = orderService.getOrdersByCompanies();
        assertNotNull(testMap);
        assertEquals(new HashMap().getClass(), testMap.getClass());
    }

    @Test
    void getOrdersByTagsTest() {
        Map<String, String> testMap = orderService.getOrdersByTags();
        assertNotNull(testMap);
        assertEquals(new HashMap().getClass(), testMap.getClass());
    }

    @Test
    void getOrdersByCategoriesTest() {
        Map<String, String> testMap = orderService.getOrdersByCategories();
        assertNotNull(testMap);
        assertEquals(new HashMap().getClass(), testMap.getClass());
    }

    @Test
    void createOrder() {
    }
}