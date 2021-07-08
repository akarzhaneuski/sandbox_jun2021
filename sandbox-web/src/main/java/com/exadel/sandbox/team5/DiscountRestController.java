package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.ReviewDto;

import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.ReviewService;

import com.exadel.sandbox.team5.dto.search.DiscountSearchCriteria;
import com.exadel.sandbox.team5.service.*;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountRestController {

    private final DiscountService service;
    private final ReviewService reviewService;
    private final OrderService orderService;
    private final ImageClientService imageService;

    @GetMapping("/{id}")
    public DiscountDto getDiscount(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<DiscountDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public DiscountDto save(@RequestBody DiscountDto entity, MultipartFile file) {
        entity.setImageId(imageService.save(file));
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

    @PostMapping("/search")
    public Page<DiscountDto> getByCriteria(@RequestBody DiscountSearchCriteria searchCriteria) {
        return service.getByCriteria(searchCriteria);
    }

    @GetMapping("/statistic")
    public Map<String, String> getStatistic() {
        return orderService.getOrdersByDiscounts();
    }

    @GetMapping("/statistic/views")
    public Map<String, String> getViewsStatistic() {
        return service.getViewsByDiscounts();
    }

    @PutMapping("/{id}/views")
    public void increaseViews(@PathVariable Long id){
        service.incrementViews(id);
    }
}

