package com.exadel.sandbox.team5;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exadel.sandbox.team5.entity.DemoEntity;
import com.exadel.sandbox.team5.service.DemoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoRestController {

    private final DemoService service;

    @GetMapping("/{id}")
    public DemoEntity getDemo(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<DemoEntity> getAll() {
        return service.getAll();
    }

    @PostMapping
    public DemoEntity save(@RequestBody DemoEntity entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public DemoEntity update(@PathVariable Long id, @RequestBody DemoEntity entity) {
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
