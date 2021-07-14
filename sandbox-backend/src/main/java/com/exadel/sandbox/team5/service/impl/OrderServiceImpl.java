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
public class OrderServiceImpl extends CRUDServiceDtoImpl<OrderDAO, Order, OrderDto> implements OrderService {


    private final EmployeeService employeeService;
    private final DiscountService discountService;
    private final DiscountDAO discountDAO;
    private final CompanyDAO companyDAO;


    public OrderServiceImpl(OrderDAO orderDAO, MapperConverter mapper, EmployeeService employeeService, DiscountService discountService, DiscountDAO discountDAO, CompanyDAO companyDAO) {
        super(orderDAO, Order.class, OrderDto.class, mapper);
        this.employeeService = employeeService;
        this.discountService = discountService;
        this.discountDAO = discountDAO;
        this.companyDAO = companyDAO;
    }

    @Override
    public OrderDto invalidatePromoCode(Long discountId, String promoCode) {

        Order selectedOrder = entityDao.getOrderByDiscountIdAndEmployeePromocode(discountId, promoCode);

        if (selectedOrder != null && selectedOrder.getPromoCodePeriodEnd().getTime() > new Date().getTime()) {
            entityDao.setPromoCodeStatus(false, promoCode);
            return mapper.map(selectedOrder, OrderDto.class);
        }
        throw new NoSuchElementException();
    }

    @Override
    public String createOrder(CreateOrder createOrder) {

        if (discountService.getById(createOrder.getDiscountId()) == null) {
            throw new IllegalArgumentException("Discount id is null!");
        }
        var employee = employeeService.getByLogin(securityUtils.getCurrentUsername());

        if (activeOrdersByTime(activeOrdersByStatus(employee)).size() < createOrder.getMaxOrderSize()) {
            String employeePromocode = new ValidatePromoCodeGenerator().generateUUID();
            var now = LocalDateTime.now();
            var orderToSave = Order.builder()
                    .discount(mapper.map(discountService.getById(createOrder.getDiscountId()), Discount.class))
                    .employee(employeeService.getById(employee.getId()))
                    .employeePromocode(employeePromocode)
                    .promoCodeStatus(true)
                    .promoCodePeriodStart(localDateTimeToDate(now))
                    .promoCodePeriodEnd(localDateTimeToDate(now.plusDays(createOrder.getAmountDiscountDays())))
                    .build();

            entityDao.save(orderToSave);
            return employeePromocode;
        }
        throw new IllegalArgumentException("Your discount limit is exceeded");
    }

    private Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private List<Order> activeOrdersByStatus(Employee employee) {
        return entityDao.findAllByEmployeeId(employee.getId()).stream().filter(Order::getPromoCodeStatus).collect(Collectors.toList());
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
        return entityDao.getAllOrdersForTags().stream().collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }
}
