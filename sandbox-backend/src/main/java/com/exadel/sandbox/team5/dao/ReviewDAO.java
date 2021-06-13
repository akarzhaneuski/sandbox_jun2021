package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Review;

import java.util.List;

public interface ReviewDAO extends CommonRepository<Review> {

    List<Review> findAllByDiscountId(Long id);
}
