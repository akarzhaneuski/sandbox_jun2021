package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<Discount> getAll() {
        return service.getAll();
    }
    //Возвращаю при пустом поле поиска или отсутсвие заданых критериев пока пустой список
    @GetMapping("/search")
    public List<Discount> getBySearchWord(@PathVariable String searchWord) {
        List<Discount> result = new ArrayList<>();
        if (searchWord != null && !searchWord.isEmpty()) {
            result = service.getByNameContaining(searchWord);
            if (result.isEmpty()) {
                result = service.getByDescriptionContaining(searchWord);
            }
        }
        return result;
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

