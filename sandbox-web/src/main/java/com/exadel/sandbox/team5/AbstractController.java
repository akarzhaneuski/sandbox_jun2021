package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.BaseEntity;
import com.exadel.sandbox.team5.service.CRUDService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractController<E extends BaseEntity, S extends CRUDService<E>> implements CommonController<E> {

    protected final S service;

    @Override
    public E getDemo(Long id) {
        return service.getById(id);
    }

    @Override
    public List getAll() {
        return service.getAll();
    }

    @Override
    public E save(E entity) {
        return service.save(entity);
    }

    @Override
    public E update(Long id, E entity) {
        entity.setId(id);
        return service.update(entity);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
