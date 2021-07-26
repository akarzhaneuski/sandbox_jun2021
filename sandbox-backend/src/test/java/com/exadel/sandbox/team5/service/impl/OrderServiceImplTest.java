package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Image;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.EmployeeService;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.util.SecurityUtils;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
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
    CompanyDAO companyDAO;

    @Spy
    DiscountDto discountDto;

    @Mock
    MapperConverter mapperConverter;

    @Spy
    Discount discount;

    @Mock
    Image image;

    @Mock
    ReviewDAO reviewDAO;

    @Mock
    DiscountService discountService;

    @Mock
    DiscountDAO discountDAO;

    @Spy
    SecurityUtils securityUtils;

    @Spy
    OrderDAO orderDAO;

//    @InjectMocks
//    OrderServiceImpl orderService = new OrderServiceImpl(orderDAO, mapperConverter, employeeService, discountService, discountDAO, companyDAO, securityUtils);


    @Mock
    OrderService orderSer;

    @InjectMocks
    OrderServiceImpl orderService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployeePromoCode() throws NoSuchElementException {
        Mockito.when(discountService.getById(1L)).thenReturn(new DiscountDto());
        Mockito.when(discountService.getById(1L)).thenReturn(new DiscountDto());
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