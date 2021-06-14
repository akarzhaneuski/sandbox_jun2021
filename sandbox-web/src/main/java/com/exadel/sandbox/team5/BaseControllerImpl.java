package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.BaseEntity;
import com.exadel.sandbox.team5.service.CRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class BaseControllerImpl<E extends BaseEntity, S extends CRUDService<E>>{

    protected final S service;

    @GetMapping("/{id}")
    public E getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List getAll() {
        return service.getAll();
    }

    @PostMapping
    public E save(E entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public E update(@PathVariable Long id, E entity) {
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
