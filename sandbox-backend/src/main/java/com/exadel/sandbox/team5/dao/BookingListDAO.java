package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.BookingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingListDAO extends JpaRepository<BookingList, Long> {

    List<BookingList> getAllByEmployeeId(Long id);

    BookingList getBookingListByDiscountIdAndEmployeePromocode(Long id, String promoCode);

    @Transactional
    @Modifying
    @Query("update BookingList o set o.promoCodeStatus = :status where o.employeePromocode = :promoCode")
    void setPromoCodeStatus(@Param("status") Boolean status, @Param("promoCode") String promoCode);
}