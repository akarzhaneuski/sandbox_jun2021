package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.util.RateSetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewDAO extends JpaRepository<Review, Long> {

    @Query(value = "SELECT AVG (r.rate) from Review r where r.id= :id")
    Double findRate(Long id);

    List<Review> findAllByDiscountId(Long id);

    @Query(value = "SELECT new com.exadel.sandbox.team5.util.RateSetter(r.rate, r.discount.id)\n" +
            "FROM Review r\n" +
            "WHERE r.discount.id IN (:list)\n" +
            "GROUP BY r.discount.id, rate")
    List<RateSetter> getRateByDiscountId(@Param("list") List<Long> discountId);
}
