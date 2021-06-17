package com.exadel.sandbox.team5.service.Validation;

import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.entity.Orders;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.EmployeeService;
import com.exadel.sandbox.team5.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AddOrder {
    private final OrderDAO orderDAO;
    private final EmployeeService employeeService;
    private final DiscountService discountService;
    private final OrderService orderService;
    private final int maxOrderSize = 5;


    public Orders createOrder(Long discountId) {
        Employee employee = employeeService.getById(1L);// TODO insert employee id

        if (discountService.getById(discountId) != null) {

            if (activeOrderByTime(activeOrderByStatus(employee)).size() < maxOrderSize) {
                Orders orders = new Orders();
                orders.setDiscount(discountService.getById(discountId));
                orders.setEmployee(employeeService.getById(employee.getId()));
                orders.setEmployeePromocode(UUIDgenerator());
                orders.setPromoCodeStatus(true);
                long currentTime = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
                orders.setPromoCodePeriodStart(new Date(currentTime));
                orders.setPromoCodePeriodEnd(new Date(currentTime + 24 * 60 * 60 * 1000));
                return orderService.save(orders);
            }

        }
        return null;
    }

    private List<Orders> activeOrderByStatus(Employee employee) {
        return orderDAO.getOrderByEmployeeId(employee.getId()).stream().filter(e -> e.getPromoCodeStatus()).collect(Collectors.toList());
    }

    private List<Orders> activeOrderByTime(List<Orders> activeOrders) {
        return activeOrders.stream().filter(e -> System.currentTimeMillis() < e.getPromoCodePeriodEnd().getTime()).collect(Collectors.toList());
    }

    private String UUIDgenerator() {
        return UUID.randomUUID().toString();
    }
}
