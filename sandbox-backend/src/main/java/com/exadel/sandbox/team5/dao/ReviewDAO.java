package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewDAO extends JpaRepository<Review, Long> {

    @Query(value = "SELECT AVG (r.rate) from Review r where r.id= :id")
    Double findRate(Long id);

    List<Review> findAllByDiscountId(Long id);

    @Query("SELECT discount.id FROM Review  WHERE rate =:rate")
    List<Long> getDiscountsIdByRate(@Param("rate") Long rate);
}
