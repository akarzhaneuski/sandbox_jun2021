package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Orders;
import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.service.*;
import com.exadel.sandbox.team5.service.Validation.AddOrder;
import com.exadel.sandbox.team5.service.Validation.InvalidateOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountRestController {

    private final DiscountService service;
    private final ReviewService reviewService;
    private final AddOrder addOrder;
    private final InvalidateOrder invalidateOrder;

    @GetMapping("/{id}")
    public Discount getDiscount(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Discount> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Discount save(@RequestBody Discount entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public Discount update(@PathVariable Long id, @RequestBody Discount entity) {
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{discountId}/reviews")
    public List<Review> getReviewsByDiscount(@PathVariable Long discountId) {
        return reviewService.getReviewsByDiscount(discountId);
    }

    @PostMapping("/{discountId}/order")
    public Orders saveOrder(@PathVariable Long discountId) {
        return addOrder.createOrder(discountId);
    }

    @GetMapping("/{discountId}/{promoCode}/validate")
    public Orders invalidatePromoCode(@PathVariable Long discountId, @PathVariable String promoCode) {
        return invalidateOrder.invalidatePromoCode(discountId, promoCode);
    }


}

