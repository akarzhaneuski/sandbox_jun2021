package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.ReviewDto;
import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl extends CRUDServiceDtoImpl<ReviewDAO, Review, ReviewDto> implements ReviewService {

    public ReviewServiceImpl(ReviewDAO reviewDAO, MapperConverter mapper) {
        super(reviewDAO, Review.class, ReviewDto.class, mapper);
    }

    @Override
    public List<ReviewDto> getReviewsByDiscount(Long id) {
        return mapper.mapAll(entityDao.findAllByDiscountId(id), ReviewDto.class);
    }
}
