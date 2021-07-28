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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

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
    OrderDAO entityDao;

    @Test
    void createOrder_ReturnIllegalArgumentExceptionWhenDiscountNotFoundDyDiscountId() throws IllegalArgumentException {
        String discountId = "3";

        when(discountService.getById(Mockito.anyLong())).thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrder(discountId);
        });
    }

    @Test
    void createOrder_ReturnEmployeePromoCodeInputCorrectDiscountId() {
        String discountId = "3";
        orderService.amountDiscountDays = "7";
        String userName = "E00001";
        DiscountDto discountDto = new DiscountDto();

        when(discountService.getById(anyLong())).thenReturn(discountDto);
        DiscountDto discountDto1 = discountService.getById(anyLong());
        mockStatic(SecurityUtils.class);
        when(SecurityUtils.getCurrentUsername()).thenReturn(userName);
        when(employeeService.getByLogin(SecurityUtils.getCurrentUsername())).thenReturn(new Employee());
        String result = orderService.createOrder(discountId);

        assertEquals(String.class, result.getClass());
    }

    @Test
    void getOrdersByDiscount_ReturnHashMap() {
        Map<String, String> resultMap = orderService.getOrdersByDiscounts();

        assertNotNull(resultMap);
        assertEquals(HashMap.class, resultMap.getClass());
    }

    @Test
    void getOrdersByCompanies_ReturnHashMap() {
        Map<String, String> resultMap = orderService.getOrdersByCompanies();

        assertNotNull(resultMap);
        assertEquals(HashMap.class, resultMap.getClass());
    }

    @Test
    void getOrdersByTags_ReturnHashMap() {
        Map<String, String> resultMap = orderService.getOrdersByTags();

        assertNotNull(resultMap);
        assertEquals(HashMap.class, resultMap.getClass());
    }

    @Test
    void getOrdersByCategories_ReturnHashMap() {
        Map<String, String> resultMap = orderService.getOrdersByCategories();

        assertNotNull(resultMap);
        assertEquals(HashMap.class, resultMap.getClass());
    }
}