package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.service.impl.ReviewServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewRestControllerImpl extends BaseControllerImpl<Review, ReviewServiceImpl> {

    ReviewRestControllerImpl(ReviewServiceImpl service) {
        super(service);
    }
}