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
public class ReviewServiceImpl extends CRUDServiceDtoImpl<ReviewDto, ReviewDAO, Review> implements ReviewService {

    public ReviewServiceImpl(ReviewDAO entityDao, Review entity, ReviewDto entityDto, MapperConverter mapper) {
        super(entityDao, entity, entityDto, mapper);
    }

    @Override
    public List<ReviewDto> getReviewsByDiscount(Long id) {
        return mapper.mapAll(entityDao.findAllByDiscountId(id), ReviewDto.class);
    }
}
