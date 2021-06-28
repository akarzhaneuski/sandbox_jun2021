package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.util.PairLD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ReviewDAO extends JpaRepository<Review, Long> {

    @Query(value = "SELECT AVG (r.rate) from Review r where r.id= :id")
    Double findRate(Long id);

    List<Review> findAllByDiscountId(Long id);

    @Query(value = """
            SELECT new com.exadel.sandbox.team5.util.PairLD(r.discount.id, AVG(r.rate))
            FROM Review r
            WHERE r.discount.id IN (:list)
            GROUP BY r.discount.id""")
    List<PairLD> getRateByDiscountId(@Param("list") Set<Long> discountId);
}
