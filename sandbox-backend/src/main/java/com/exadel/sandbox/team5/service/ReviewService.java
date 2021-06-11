package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService extends CRUDService<Review> {
    Page<Review> findAll(Pageable pageable);

    Page<Review> findByDiscountId(Long id, Pageable pageable);
}
