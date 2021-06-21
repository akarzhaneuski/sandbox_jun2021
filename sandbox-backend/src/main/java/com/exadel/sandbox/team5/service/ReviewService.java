package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.ReviewDto;

import java.util.List;

public interface ReviewService extends CRUDService<ReviewDto> {

    List<ReviewDto> getReviewsByDiscount(Long id);
}
