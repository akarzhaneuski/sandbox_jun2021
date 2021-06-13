package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.service.impl.ReviewServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewRestController extends AbstractController<Review, ReviewServiceImpl> {

    ReviewRestController(ReviewServiceImpl service) {
        super(service);
    }

    @GetMapping("/{discountId}/reviews")
    public List<Review> getReviewByDiscountId(@PathVariable Long discountId) {
        return service.getListEntityByOtherEntityId(discountId);
    }
}