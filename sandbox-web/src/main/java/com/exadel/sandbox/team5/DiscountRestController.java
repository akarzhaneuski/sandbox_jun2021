package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.service.DiscountService;
import error.MyEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor

public class DiscountRestController {

    private final DiscountService service;
    private final DiscountDAO discountDAO;

    @GetMapping("/{id}")
    public Discount getDiscount(@PathVariable Long id) {
//        return service.getById(id);
        return discountDAO.findById(id).orElseThrow(()->new MyEntityNotFoundException(id));
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

}

