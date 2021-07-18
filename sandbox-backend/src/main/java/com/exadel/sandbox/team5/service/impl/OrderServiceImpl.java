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
import com.exadel.sandbox.team5.util.Pair;
import com.exadel.sandbox.team5.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderServiceImpl extends CRUDServiceDtoImpl<OrderDAO, Order, OrderDto> implements OrderService {


    private final EmployeeService employeeService;
    private final DiscountService discountService;
    private final DiscountDAO discountDAO;
    private final CompanyDAO companyDAO;
    private final SecurityUtils securityUtils;

    @Value("${constant.amountDiscountDays}")
    String amountDiscountDays;



    public OrderServiceImpl(OrderDAO orderDAO, MapperConverter mapper, EmployeeService employeeService,
                            DiscountService discountService, DiscountDAO discountDAO, CompanyDAO companyDAO, SecurityUtils securityUtils) {
        super(orderDAO, Order.class, OrderDto.class, mapper);
        this.employeeService = employeeService;
        this.discountService = discountService;
        this.discountDAO = discountDAO;
        this.companyDAO = companyDAO;
        this.securityUtils = securityUtils;
    }

    @Override
    public void invalidatePromoCode(String uuid) {
        var selectedOrder = entityDao.getOrderByEmployeePromocode(uuid);
        if (selectedOrder != null && selectedOrder.getPromoCodePeriodEnd().getTime() > new Date().getTime()) {
            entityDao.setPromoCodeStatus(false, uuid);
        }
    }

    @Override
    public String createOrder(String discountId) {

        Long discountIdL = Long.valueOf(discountId);

        if (discountService.getById(discountIdL) != null) {
            var employee = employeeService.getByLogin(securityUtils.getCurrentUsername());

            String employeePromocode = new ValidatePromoCodeGenerator().generateUUID();
            var now = LocalDateTime.now();
            var orderToSave = Order.builder()
                    .discount(mapper.map(discountService.getById(discountIdL), Discount.class))
                    .employee(employeeService.getById(employee.getId()))
                    .employeePromocode(employeePromocode)
                    .promoCodeStatus(true)
                    .promoCodePeriodStart(localDateTimeToDate(now))

                    .promoCodePeriodEnd(localDateTimeToDate(now.plusDays(Long.valueOf(amountDiscountDays))))
                    .build();

            entityDao.save(orderToSave);

            return employeePromocode;
        }
        throw new IllegalArgumentException("Discount not found");
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

    @Override
    public Map<String, String> getOrdersByCategories() {
        return entityDao.getAllOrdersForCategories().stream().collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
    }
}
