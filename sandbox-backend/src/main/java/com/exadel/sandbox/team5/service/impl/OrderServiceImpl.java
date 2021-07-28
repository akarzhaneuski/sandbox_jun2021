package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CompanyDAO;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
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
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;
import com.exadel.sandbox.team5.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderServiceImpl extends CRUDServiceDtoImpl<OrderDAO, Order, OrderDto> implements OrderService {


    private final EmployeeService employeeService;
    private final DiscountService discountService;
    private final DiscountDAO discountDAO;
    private final CompanyDAO companyDAO;

    @Value("${constant.amountDiscountDays}")
    String amountDiscountDays;

    public OrderServiceImpl(OrderDAO orderDAO, MapperConverter mapper, EmployeeService employeeService,
                            DiscountService discountService, DiscountDAO discountDAO, CompanyDAO companyDAO) {
        super(orderDAO, Order.class, OrderDto.class, mapper);
        this.employeeService = employeeService;
        this.discountService = discountService;
        this.discountDAO = discountDAO;
        this.companyDAO = companyDAO;
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

        var discountIdL = Long.valueOf(discountId);
        if (discountService.getById(discountIdL) != null) {
            var employee = employeeService.getByLogin(SecurityUtils.getCurrentUsername());
            String employeePromocode = new ValidatePromoCodeGenerator().generateUUID();
            var now = LocalDateTime.now();
            var orderToSave = Order.builder()
                    .discount(mapper.map(discountService.getById(discountIdL), Discount.class))
                    .employee(mapper.map(employeeService.getById(employee.getId()), Employee.class))
                    .employeePromocode(employeePromocode)
                    .promoCodeStatus(true)
                    .promoCodePeriodStart(localDateTimeToDate(now))
                    .promoCodePeriodEnd(localDateTimeToDate(now.plusDays(Long.parseLong(amountDiscountDays))))
                    .build();
            entityDao.save(orderToSave);
            return employeePromocode;
        }
        throw new IllegalArgumentException("Discount not found");
    }

    private Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Map<String, String> getOrdersByDiscounts() {
        Map<String, String> result = new HashMap<>();
        discountDAO.getAllOrdersForDiscounts().forEach(p -> result.put(p.getFirst(), p.getSecond()));
        return result;
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

    public ResultPage<DiscountDto> getAll(SearchCriteria searchCriteria) {
        var employee = employeeService.getByLogin(SecurityUtils.getCurrentUsername());
        var orders = entityDao.findOrderByEmployeeId(employee.getId(), searchCriteria.getPageRequest());
        return discountService.mapDto(orders);
    }
}
