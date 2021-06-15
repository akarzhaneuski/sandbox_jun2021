package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.entity.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl extends CRUDServiceImpl<Review> {

    public ReviewServiceImpl(ReviewDAO repository) {
        super(repository);
    }
}
