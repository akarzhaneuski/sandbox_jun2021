package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.EmployeeService;
import com.exadel.sandbox.team5.util.SecurityUtils;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class OrderServiceImplTest {

    @Mock
    EmployeeService employeeService;

    @Mock
    DiscountService discountService;

    @Mock
    MapperConverter mapperConverter;

    @Mock
    CompanyDAO companyDAO;

    @Mock
    DiscountDAO discountDAO;

    @Mock
    SecurityUtils securityUtils;

    @Mock
    OrderDAO orderDAO;

    @InjectMocks
    OrderServiceImpl orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    void testCreateOrderException() throws IllegalArgumentException {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Discount not found");
        when(discountService.getById(Mockito.anyLong())).thenReturn(null);
    }

    @Test
    void testCreateOrder() {
        when(discountService.getById(Mockito.anyLong())).thenReturn(new DiscountDto());
        mockStatic(SecurityUtils.class);
        when(SecurityUtils.getCurrentUsername()).thenReturn("E00001");
        when(employeeService.getByLogin(SecurityUtils.getCurrentUsername())).thenReturn(new Employee());
        orderService.amountDiscountDays = "7";
        assertEquals(String.class, orderService.createOrder("3").getClass());
    }

    @Test
    void getOrdersByDiscountsTest() {
        Map<String, String> testMap = orderService.getOrdersByDiscounts();
        assertNotNull(testMap);
        assertEquals(HashMap.class, testMap.getClass());
    }

    @Test
    void getOrdersByCompaniesTest() {
        assertEquals(HashMap.class, orderService.getOrdersByCompanies().getClass());
    }

    @Test
    void getOrdersByTagsTest() {
        assertEquals(HashMap.class, orderService.getOrdersByTags().getClass());
    }

    @Test
    void getOrdersByCategoriesTest() {
        assertEquals(HashMap.class, orderService.getOrdersByCategories().getClass());
    }
}