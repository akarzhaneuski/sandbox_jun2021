package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;

    @Override
    public Review getById(Long id) {
        return reviewDAO.findById(id).orElse(null);
    }

    @Override
    public List<Review> getAll() {
        return reviewDAO.findAll();
    }

    @Override
    public Review save(Review review) {
        return reviewDAO.save(review);
    }

    @Override
    public Review update(Review review) {
        return reviewDAO.save(review);
    }

    @Override
    public void delete(Long id) { reviewDAO.deleteById(id); }
}
