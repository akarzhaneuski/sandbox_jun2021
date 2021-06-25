package com.exadel.sandbox.team5.controller;

import com.exadel.sandbox.team5.dto.ReviewDto;
import com.exadel.sandbox.team5.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewService service;

    @GetMapping("/{id}")
    public ReviewDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<ReviewDto> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ReviewDto save(@RequestBody ReviewDto entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ReviewDto update(@PathVariable Long id, ReviewDto entity) {
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

