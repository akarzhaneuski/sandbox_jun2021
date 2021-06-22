package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.entity.Order;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.EmployeeService;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.ValidatePromoCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final EmployeeService employeeService;
    private final DiscountService discountService;
    private final MapperConverter mapper;
    private final int maxOrderSize = 1;
    private final int amountDiscountDays = 7;

    @Override
    public Order getById(Long id) {
        return orderDAO.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Order> getAll() {
        return orderDAO.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderDAO.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderDAO.save(order);
    }

    @Override
    public void delete(Long id) {
        orderDAO.deleteById(id);
    }


    public Order invalidatePromoCode(Long discountId, String promoCode) {

        Order selectedOrder = orderDAO.getBookingListByDiscountIdAndEmployeePromocode(discountId, promoCode);

        if (selectedOrder != null && selectedOrder.getPromoCodePeriodEnd().getTime() > new Date().getTime()) {
            orderDAO.setPromoCodeStatus(false, promoCode);
            return selectedOrder;
        }
        throw new NoSuchElementException();
    }


    public Order createOrder(Long discountId) {
        Employee employee = employeeService.getById(1L);//TODO should be fix after security merge

        if (discountService.getById(discountId) != null) {

            if (activeOrdersByTime(activeOrdersByStatus(employee)).size() < maxOrderSize) {
                Order order = new Order();
                order.setDiscount(mapper.map(discountService.getById(discountId), Discount.class));
                order.setEmployee(employeeService.getById(employee.getId()));
                order.setEmployeePromocode(new ValidatePromoCodeGenerator().generateUUID());
                order.setPromoCodeStatus(true);

                Date currentDate = new Date();
                LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                localDateTime = localDateTime.plusDays(amountDiscountDays);
                Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                order.setPromoCodePeriodStart(currentDate);
                order.setPromoCodePeriodEnd(currentDatePlusOneDay);

                return orderDAO.save(order);
            }

        }
        throw new NoSuchElementException();
    }

    private List<Order> activeOrdersByStatus(Employee employee) {
        return orderDAO.findAllByEmployeeId(employee.getId()).stream().filter(Order::getPromoCodeStatus).collect(Collectors.toList());
    }

    private List<Order> activeOrdersByTime(List<Order> activeOrders) {
        return activeOrders.stream().filter(e -> System.currentTimeMillis() < e.getPromoCodePeriodEnd().getTime()).collect(Collectors.toList());
    }
}
