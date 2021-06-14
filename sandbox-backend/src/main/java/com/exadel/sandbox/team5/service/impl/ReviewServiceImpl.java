package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl extends CRUDServiceImpl<Review, ReviewDAO> {

    public ReviewServiceImpl(ReviewDAO repository) {
        super(repository);
    }

    public List<Review> getReviewsByDiscountId(Long id) {
        return repository.findAllByDiscountId(id);
    }
}
