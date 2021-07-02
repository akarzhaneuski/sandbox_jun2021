package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.dto.OrderDto;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.entity.Order;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.EmployeeService;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.ValidatePromoCodeGenerator;
import com.exadel.sandbox.team5.util.CreateOrder;
import com.exadel.sandbox.team5.util.Pair;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
//@EnableScheduling
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;
    private final EmployeeService employeeService;
    private final DiscountService discountService;
    private final MapperConverter mapper;
    private final DiscountDAO discountDAO;
    private final CompanyDAO companyDAO;
    private final int delayToInvalidateOrder = 5000;


    @Override
    public OrderDto getById(Long id) {
        return mapper.map(orderDAO.findById(id).orElseThrow(NoSuchElementException::new), OrderDto.class);
    }

    @Override
    public List<OrderDto> getAll() {
        return mapper.mapAll(orderDAO.findAll(), OrderDto.class);
    }

    @Override
    public OrderDto save(OrderDto order) {
        return mapper.map(orderDAO.saveAndFlush(mapper.map((order), Order.class)), OrderDto.class);
    }

    @Override
    public OrderDto update(OrderDto order) {
        return this.save(order);
    }

    @Override
    public void delete(Long id) {
        orderDAO.deleteById(id);
    }

    @Override
    public OrderDto invalidatePromoCode(Long discountId, String promoCode) {

        Order selectedOrder = orderDAO.getOrderByDiscountIdAndEmployeePromocode(discountId, promoCode);

        if (selectedOrder != null && selectedOrder.getPromoCodePeriodEnd().getTime() > new Date().getTime()) {
            orderDAO.setPromoCodeStatus(false, promoCode);
            return mapper.map(selectedOrder, OrderDto.class);
        }
        throw new NoSuchElementException();
    }

    @Override
    public OrderDto createOrder(CreateOrder createOrder) {

        Employee employee = employeeService.getById(1L);//TODO should be fix after security merge

        if (discountService.getById(createOrder.getDiscountId()) != null) {

            if (activeOrdersByTime(activeOrdersByStatus(employee)).size() < createOrder.getMaxOrderSize()) {
                Order order = new Order();
                order.setDiscount(mapper.map(discountService.getById(createOrder.getDiscountId()), Discount.class));
                order.setEmployee(employeeService.getById(employee.getId()));
                order.setEmployeePromocode(new ValidatePromoCodeGenerator().generateUUID());
                order.setPromoCodeStatus(true);
                Date currentDate = new Date();
                LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                //localDateTime = localDateTime.plusDays(createOrder.getAmountDiscountDays());
                localDateTime = localDateTime.plusMinutes(1);
                Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                order.setPromoCodePeriodStart(currentDate);
                order.setPromoCodePeriodEnd(currentDatePlusOneDay);

                return mapper.map(orderDAO.save(order), OrderDto.class);
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

    @Override
    public Map<String, String> getOrdersByDiscounts() {
        return discountDAO.getAllOrdersForDiscounts().stream().collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    @Override
    public Map<String, String> getOrdersByCompanies() {
        return companyDAO.getAllOrdersForCompanies().stream().collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

    @Override
    public Map<String, String> getOrdersByTags() {
        return orderDAO.getAllOrdersForTags().stream().collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }

//    @Scheduled(fixedRate = delayToInvalidateOrder)
//    public void reportCurrentTime() {
//        log.info("*******************");
//        orderDAO.setPromoCodeStatusAfterExpirationTime(new Date());
//    }
}
