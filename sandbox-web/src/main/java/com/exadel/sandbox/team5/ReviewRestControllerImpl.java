package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.service.impl.ReviewServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewRestControllerImpl extends BaseControllerImpl<Review, ReviewServiceImpl> {

    ReviewRestControllerImpl(ReviewServiceImpl service) {
        super(service);
    }

    @GetMapping("/{discountId}/reviews")
    public List<Review> getReviewByDiscountId(@PathVariable Long discountId) {
        return service.getReviewsByDiscountId(discountId);
    }
}