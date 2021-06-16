package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.DiscountDTO;
import com.exadel.sandbox.team5.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor

public class DiscountRestController {

    private final DiscountService service;

    @GetMapping("/{id}")
    public DiscountDTO getDiscount(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<DiscountDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public DiscountDTO save(@RequestBody DiscountDTO entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public DiscountDTO update(@PathVariable Long id, @RequestBody DiscountDTO entity) {
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}

