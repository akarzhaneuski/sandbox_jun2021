package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl extends AbstractService<Review, ReviewDAO> implements ReviewService {

    public ReviewServiceImpl(ReviewDAO repository) {
        super(repository);
    }

    @Override
    public List<Review> getReviewsByDiscountId(Long id) {
        return repository.findReviewByDiscountId(id);
    }
}
