package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.BookingListDAO;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.entity.BookingList;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.service.BookingListService;
import com.exadel.sandbox.team5.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class BookingListServiceImpl implements BookingListService {

    private final BookingListDAO bookingListDAO;
    private final EmployeeService employeeService;
    private final DiscountDAO discountDAO;
    private final int maxOrderSize = 20;

    @Override
    public BookingList getById(Long id) {
        return bookingListDAO.findById(id).orElse(null);
    }

    @Override
    public List<BookingList> getAll() {
        return bookingListDAO.findAll();
    }

    @Override
    public BookingList save(BookingList bookingList) {
        return bookingListDAO.save(bookingList);
    }

    @Override
    public BookingList update(BookingList bookingList) {
        return bookingListDAO.save(bookingList);
    }

    @Override
    public void delete(Long id) {
        bookingListDAO.deleteById(id);
    }

    public BookingList invalidatePromoCode(Long discountId, String promoCode) {

        BookingList selectedOrder = bookingListDAO.getBookingListByDiscountIdAndEmployeePromocode(discountId, promoCode);

        if (selectedOrder != null && selectedOrder.getPromoCodePeriodEnd().getTime() > new Date().getTime()) {
            bookingListDAO.setPromoCodeStatus(false, promoCode);
            return selectedOrder;
        }

        return null;
    }

    public BookingList createOrder(Long discountId) {
        Employee employee = employeeService.getById(1L);// TODO insert employee id

        if (discountDAO.getById(discountId) != null) {

            if (activeOrdersByTime(activeOrdersByStatus(employee)).size() < maxOrderSize) {
                BookingList bookingList = new BookingList();
                bookingList.setDiscount(discountDAO.getById(discountId));
                bookingList.setEmployee(employeeService.getById(employee.getId()));
                String promoCode = generateUUID();
                log.info("*************" + promoCode);
                bookingList.setEmployeePromocode(generateUUID());
                bookingList.setPromoCodeStatus(true);

                Date currentDate = new Date();

                LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                localDateTime = localDateTime.plusDays(1);
                Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                bookingList.setPromoCodePeriodStart(currentDate);
                bookingList.setPromoCodePeriodEnd(currentDatePlusOneDay);

                return bookingListDAO.save(bookingList);
            }

        }
        return null;
    }

    private List<BookingList> activeOrdersByStatus(Employee employee) {
        return bookingListDAO.getAllByEmployeeId(employee.getId()).stream().filter(e -> e.getPromoCodeStatus()).collect(Collectors.toList());
    }

    private List<BookingList> activeOrdersByTime(List<BookingList> activeOrders) {
        return activeOrders.stream().filter(e -> System.currentTimeMillis() < e.getPromoCodePeriodEnd().getTime()).collect(Collectors.toList());
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
