package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.entity.Review;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl extends AbstractService<Review, ReviewDAO> {

    public ReviewServiceImpl(ReviewDAO repository) {
        super(repository);
    }

    @Override
    public List<Review> getListEntityByOtherEntityId(Long id) {
        return repository.findAllByDiscountId(id);
    }
}
