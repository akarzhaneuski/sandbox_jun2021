package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.entity.Image;
import com.exadel.sandbox.team5.entity.Order;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.EmployeeService;
import com.exadel.sandbox.team5.service.OrderService;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class OrderServiceImplTest {

    static final String amountDiscountDays = "7";

    @Mock
    EmployeeService employeeService;

    @Mock
    CompanyDAO companyDAO;

    @Mock
    DiscountDto discountDto;

    @Mock
    MapperConverter mapperConverter;

    @Mock
    Discount discount;

    @Mock
    Image image;

    @Mock
    ReviewDAO reviewDAO;

    @Mock
    DiscountService discountService;

    @Mock
    DiscountDAO discountDAO;

    @Mock
    SecurityUtils securityUtils;

    @Mock
    OrderDAO orderDAO;

    @Mock
    OrderService orderSer;

    @InjectMocks
    OrderServiceImpl orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

//    @BeforeEach
//    void init() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void testInvalidatePromoCode(){
        Order selectedOrder = new Order();
        var now = LocalDateTime.now();
        var periodStart = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        var periodEnd = Date.from((now.plusDays(Long.parseLong(amountDiscountDays))).atZone(ZoneId.systemDefault()).toInstant());
        selectedOrder.setPromoCodeStatus(true);
        selectedOrder.setPromoCodePeriodStart(periodStart);
        selectedOrder.setPromoCodePeriodEnd(periodEnd);
        when(orderService.entityDao.getOrderByEmployeePromocode("87f532ab-3ded-4930-aa5d-02107f78ae9c")).
                thenReturn(selectedOrder);
        do(selectedOrder.setP;).when(orderService.entityDao.setPromoCodeStatus(false, "87f532ab-3ded-4930-aa5d-02107f78ae9c")).
        assertEquals("false", selectedOrder.getPromoCodeStatus());
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    void testCreateEmployeePromoCodeException() throws IllegalArgumentException {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Discount not found");
        when(discountService.getById(Mockito.anyLong())).thenReturn(null);
    }

    @Test
    void testCreateEmployeePromoCode() {
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