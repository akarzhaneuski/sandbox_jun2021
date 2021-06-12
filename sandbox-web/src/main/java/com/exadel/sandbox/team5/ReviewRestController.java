package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.DemoEntity;
import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewService service;

    @GetMapping("/{id}")
    public Review getDemo(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Review> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Review save(@RequestBody Review review) {
        return service.save(review);
    }

    @PutMapping("/{id}")
    public Review update(@PathVariable Long id, @RequestBody Review review) {
        review.setId(id);
        return service.update(review);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{discountId}/reviews")
    public List<Review> getReviewByDiscountId(@PathVariable Long discountId){
        return service.getReviewsByDiscountId(discountId);
    }

//    @GetMapping("/{id}")
//    public List<Review> save(@PathVariable Long id) {
//        if (id != null && service.getById(id) != null) {
//            return service.getAll().stream().filter(entity -> entity.getDiscount().getId().equals(id)).collect(Collectors.toList());
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown discount id");
//        }
//    }
}

