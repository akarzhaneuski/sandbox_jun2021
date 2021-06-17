package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.ReviewDto;
import com.exadel.sandbox.team5.entity.Order;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.ReviewService;
import com.exadel.sandbox.team5.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor

public class DiscountRestController {

    private final DiscountService service;
    private final ReviewService reviewService;
    private final OrderServiceImpl orderService;

    @GetMapping("/{id}")
    public DiscountDto getDiscount(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<DiscountDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public DiscountDto save(@RequestBody DiscountDto entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public DiscountDto update(@PathVariable Long id, @RequestBody DiscountDto entity) {
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{discountId}/reviews")
    public List<ReviewDto> getReviewsByDiscount(@PathVariable Long discountId) {
        return reviewService.getReviewsByDiscount(discountId);
    }

    @PostMapping("/{discountId}/orders")
    public Order saveOrder(@PathVariable Long discountId) {
        int maxOrderSize = 1;
        long amountDiscountDays = 7;
        return orderService.createOrder(discountId, maxOrderSize, amountDiscountDays);
    }

    @GetMapping("/{discountId}/codes/{promoCode}/validate")
    public Order invalidatePromoCode(@PathVariable Long discountId, @PathVariable String promoCode) {
                return orderService.invalidatePromoCode(discountId, promoCode);
    }



    @GetMapping("/search")
    public Page<Discount> getByFilters(@RequestBody SearchCriteria searchCriteria){
        return service.getByFilters(searchCriteria);
    }

}

