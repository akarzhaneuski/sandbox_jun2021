package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CommonRepository;
import com.exadel.sandbox.team5.entity.BaseEntity;

import java.util.List;

abstract class AbstractService<E extends BaseEntity, R extends CommonRepository<E>> {

    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    public E getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public E save(E discount) {
        return repository.saveAndFlush(discount);
    }

    public E update(E discount) {
        return repository.save(discount);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
