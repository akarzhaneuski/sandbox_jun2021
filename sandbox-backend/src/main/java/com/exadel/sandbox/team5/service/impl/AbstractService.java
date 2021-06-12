package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CommonRepository;
import com.exadel.sandbox.team5.entity.BaseEntity;
import com.exadel.sandbox.team5.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

abstract class AbstractService<E extends BaseEntity, R extends CommonRepository<E>> implements CRUDService<E> {

    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    @Override
    public E save(E discount) {
        return repository.saveAndFlush(discount);
    }

    @Override
    public E update(E discount) {
        return repository.save(discount);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
