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
//@RequiredArgsConstructor
public class ReviewServiceImpl extends CRUDServiceDtoImpl<ReviewDto, ReviewDAO, Review> implements ReviewService {

    public ReviewServiceImpl(ReviewDAO entityDao, Review entity, ReviewDto entityDto, MapperConverter mapper) {
        super(entityDao, entity, entityDto, mapper);
    }

//    private final ReviewDAO reviewDAO;
//    private final MapperConverter mapper;

//    @Override
//    public ReviewDto getById(Long id) {
//        return reviewDAO.findById(id).map(review->mapper.map(review, ReviewDto.class)).orElseThrow(NoSuchElementException::new);
//    }
//
//    @Override
//    public List<ReviewDto> getAll() {
//        return reviewDAO.findAll().stream().map(review->mapper.map(review, ReviewDto.class)).collect(Collectors.toList());
//    }
//
//    @Override
//    public ReviewDto save(ReviewDto reviewDto) {
//        Review review = mapper.map(reviewDto, Review.class);
//        return mapper.map(reviewDAO.saveAndFlush(review), ReviewDto.class);
//    }
//
//    @Override
//    public ReviewDto update(ReviewDto reviewDto) {
//        return this.save(reviewDto);
//    }
//
//    @Override
//    public void delete(Long id) {
//        reviewDAO.deleteById(id);
//    }

    @Override
    public List<ReviewDto> getReviewsByDiscount(Long id) {
        return mapper.mapAll(entityDao.findAllByDiscountId(id), ReviewDto.class);
    }
}
