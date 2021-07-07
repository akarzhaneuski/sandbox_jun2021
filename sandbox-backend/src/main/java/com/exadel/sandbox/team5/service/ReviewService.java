package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.ReviewDto;

import java.util.Map;

public interface ReviewService extends CRUDService<ReviewDto> {

    Map<Integer, Integer> getReviewsByDiscount(Long id);
}
