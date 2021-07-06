package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.dto.TagDto;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagRestController {

    private final TagService tagService;
    private final OrderService orderService;

    @GetMapping("/{id}")
    public TagDto getTag(@PathVariable Long id) {
        return tagService.getById(id);
    }

    @GetMapping()
    public List<TagDto> getAll() {
        return tagService.getAll();
    }

    @PostMapping
    public TagDto save(@RequestBody TagDto entity) {
        return tagService.save(entity);
    }

    @GetMapping("/statistic")
    public Map<String, String> getStatistic() {
        return orderService.getOrdersByTags();
    }

    @GetMapping("/categories/statistic")
    public Map<String, String> getStatisticByCategories(){
        return orderService.getOrdersByCategories();
    }
}
