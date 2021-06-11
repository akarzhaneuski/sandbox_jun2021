package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.DemoEntity;
import com.exadel.sandbox.team5.entity.Review;
import com.exadel.sandbox.team5.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewService service;

    @GetMapping("/{id}")
    public List<Review> save(@PathVariable Long id) {
        if (id != null && service.getById(id) != null) {
            return service.getAll().stream().filter(entity -> entity.getDiscount().getId().equals(id)).collect(Collectors.toList());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown discount id");
        }
    }
}

