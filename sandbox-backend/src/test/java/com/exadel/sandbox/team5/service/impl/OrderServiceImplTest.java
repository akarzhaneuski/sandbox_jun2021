package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.EmployeeService;
import com.exadel.sandbox.team5.util.SecurityUtils;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
class OrderServiceImplTest {

    @Mock
    EmployeeService employeeService;
    @Spy
    DiscountService discountService;
    @Mock
    DiscountDAO discountDAO;
    @Mock
    CompanyDAO companyDAO;
    @Mock
    SecurityUtils securityUtils;

    @InjectMocks
    OrderServiceImpl orderService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Mock
    OrderDAO orderDAO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInvalidatePromoCode() throws NoSuchElementException {
        assertEquals(String.class, orderService.createOrder("3"));

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