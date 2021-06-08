package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDAO extends JpaRepository<Review, Long> {
}
