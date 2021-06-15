package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.service.impl.DiscountServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountRestControllerImpl extends BaseControllerImpl<Discount, DiscountServiceImpl> {

    private final ReviewDAO reviewDAO;

    DiscountRestControllerImpl(DiscountServiceImpl service, ReviewDAO reviewDAO) {
        super(service);
        this.reviewDAO = reviewDAO;
    }

    @GetMapping("/{discountId}/reviews")
    public List<Review> getReviewByDiscountId(@PathVariable Long discountId) {
        return reviewDAO.findAllByDiscountId(discountId);
    }
}