package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.ReviewDto;
import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;
    private final MapperConverter mapper;

    @Override
    public ReviewDto getById(Long id) {
        return reviewDAO.findById(id).map(review->mapper.map(review, ReviewDto.class)).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<ReviewDto> getAll() {
        return reviewDAO.findAll().stream().map(review->mapper.map(review, ReviewDto.class)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto save(ReviewDto reviewDto) {
        Review review = mapper.map(reviewDto, Review.class);
        return mapper.map(reviewDAO.saveAndFlush(review), ReviewDto.class);
    }

    @Override
    public ReviewDto update(ReviewDto reviewDto) {
        return this.save(reviewDto);
    }

    @Override
    public void delete(Long id) {
        reviewDAO.deleteById(id);
    }

    @Override
    public Map<Integer, Integer> getReviewsByDiscount(Long id) {
        Map<Integer, Integer> countRate = reviewDAO.findAllRateByDiscountId(id).stream()
                .collect(Collectors.toMap(x -> Integer.parseInt(x.getFirst()), y -> Integer.parseInt(y.getSecond())));
        Map<Integer, Integer> result = new LinkedHashMap<>();
        for (int i = 5; i >= 1; i--) {
            result.put(i, countRate.getOrDefault(i, 0));
        }
        return result;
    }
}
