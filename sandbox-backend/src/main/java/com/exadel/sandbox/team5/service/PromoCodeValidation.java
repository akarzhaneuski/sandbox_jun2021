package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.entity.Orders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PromoCodeValidation {

    private final OrderDAO orderDAO;
    private final EmployeeService employeeService;
    private final DiscountService discountService;
    private final OrderService orderService;


    public Orders validate(Long discountId) {
        Employee employee = employeeService.getById(1L);// TODO insert employee id

        if (activeOrderByTime(activeOrderByStatus(employee)).size() < 1) {
            Orders orders = new Orders();
            orders.setDiscount(discountService.getById(discountId));
            orders.setEmployee(employeeService.getById(employee.getId()));
            orders.setEmployeePromocode(UUIDgenerator());
            orders.setPromoCodeStatus(true);
            long currentTime = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
            orders.setPromoCodePeriodStart(new Date(currentTime));
            orders.setPromoCodePeriodEnd(new Date(currentTime + 24 * 60 * 60 * 1000));
            orderService.save(orders);
            return orders;
        }

        return null;
    }

    private List<Orders> activeOrderByStatus(Employee employee) {
        return orderDAO.getOrderByEmployeeId(employee.getId()).stream().filter(e -> e.isPromoCodeStatus() == true).collect(Collectors.toList());
    }

    private List<Orders> activeOrderByTime(List<Orders> activeOrders) {
        return activeOrders.stream().filter(e -> System.currentTimeMillis() < e.getPromoCodePeriodEnd().getTime()).collect(Collectors.toList());
    }

    private String UUIDgenerator() {
        return UUID.randomUUID().toString();
    }
}
