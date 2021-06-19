package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.BookingListDAO;
import com.exadel.sandbox.team5.entity.BookingList;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.BookingListService;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class BookingListServiceImpl implements BookingListService {

    private final BookingListDAO bookingListDAO;
    private final EmployeeService employeeService;
    private final DiscountService discountService;
    private final MapperConverter mapper;
    private final int maxOrderSize = 1;

    @Override
    public BookingList getById(Long id) {
        return bookingListDAO.findById(id).orElseThrow(NoSuchElementException::new);
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
        throw new NoSuchElementException();
    }


    public BookingList createOrder(Long discountId) {
        Employee employee = employeeService.getById(1L);// TODO insert employee id

        if (discountService.getById(discountId) != null) {

            if (activeOrdersByTime(activeOrdersByStatus(employee)).size() < maxOrderSize) {
                BookingList bookingList = new BookingList();
                bookingList.setDiscount(mapper.map(discountService.getById(discountId), Discount.class));
                bookingList.setEmployee(employeeService.getById(employee.getId()));
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
        throw new NoSuchElementException();
    }

    private List<BookingList> activeOrdersByStatus(Employee employee) {
        return bookingListDAO.findAllByEmployeeId(employee.getId()).stream().filter(BookingList::getPromoCodeStatus).collect(Collectors.toList());
    }

    private List<BookingList> activeOrdersByTime(List<BookingList> activeOrders) {
        return activeOrders.stream().filter(e -> System.currentTimeMillis() < e.getPromoCodePeriodEnd().getTime()).collect(Collectors.toList());
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
