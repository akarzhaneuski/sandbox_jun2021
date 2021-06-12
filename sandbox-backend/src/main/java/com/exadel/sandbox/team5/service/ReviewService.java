package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.Review;

import java.util.List;

public interface ReviewService extends CRUDService<Review> {

    List<Review> getReviewsByDiscountId (Long id);

}
