package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.ReviewDto;
import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl extends CRUDServiceDtoImpl<ReviewDAO, Review, ReviewDto> implements ReviewService {

    private static final int MAX_RATE = 5;

    public ReviewServiceImpl(ReviewDAO reviewDAO, MapperConverter mapper) {
        super(reviewDAO, Review.class, ReviewDto.class, mapper);
    }

    @Override
    public Map<Integer, Integer> getReviewsByDiscount(Long id) {
        Map<Integer, Integer> countRate = entityDao.findAllRateByDiscountId(id).stream()
                .collect(Collectors.toMap(x -> Integer.parseInt(x.getFirst()), y -> Integer.parseInt(y.getSecond())));
        Map<Integer, Integer> result = new LinkedHashMap<>();
        for (int i = MAX_RATE; i >= 1; i--) {
            result.put(i, countRate.getOrDefault(i, 0));
        }
        return result;
    }
}
