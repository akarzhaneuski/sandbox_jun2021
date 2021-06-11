package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor

public class DiscountRestController {

    private final DiscountService service;

    @GetMapping("/{id}")
    public Discount getDiscount(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public Page<Discount> getAll() {
        Pageable pageWithFiveElements = PageRequest.of(1, 5);
        return service.getAll(pageWithFiveElements);
    }


    @GetMapping("/all/{idTag}")
    public Page<Discount> getAllByTag(@PathVariable("idTag") Long idTag) {
        Pageable pageWithFiveElements = PageRequest.of(1, 5);
        return service.getAllByTagsId(idTag, pageWithFiveElements);
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

}

