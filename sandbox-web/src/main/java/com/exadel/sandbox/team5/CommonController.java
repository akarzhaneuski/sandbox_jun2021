package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.BaseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommonController<T extends BaseEntity> {

    @GetMapping("/{id}")
    public T getDemo(@PathVariable Long id);

    @GetMapping("/all")
    public List<T> getAll();

    @PostMapping
    public T save(@RequestBody T entity);

    @PutMapping("/{id}")
    public T update(@PathVariable Long id, @RequestBody T entity);

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id);
}