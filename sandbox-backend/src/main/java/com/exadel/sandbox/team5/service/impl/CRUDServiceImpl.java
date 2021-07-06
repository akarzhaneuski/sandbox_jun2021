package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CommonRepository;
import com.exadel.sandbox.team5.entity.BaseEntity;
import com.exadel.sandbox.team5.service.CRUDService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
class CRUDServiceImpl<E extends BaseEntity, S extends CommonRepository<E>> implements CRUDService<E> {

    protected final S repository;

    public E getById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<E> getAll() {
        return repository.findAll();
    }

    public E save(E entity) {
        return repository.saveAndFlush(entity);
    }

    public E update(E entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
