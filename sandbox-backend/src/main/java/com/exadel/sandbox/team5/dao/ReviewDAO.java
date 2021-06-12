package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewDAO extends CommonRepository<Review> {

    List<Review> findReviewByDiscountId (Long id);
}
