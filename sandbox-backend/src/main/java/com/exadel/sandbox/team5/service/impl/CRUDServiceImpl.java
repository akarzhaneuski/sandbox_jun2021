package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CommonRepository;
import com.exadel.sandbox.team5.entity.BaseEntity;
import com.exadel.sandbox.team5.service.CRUDService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Getter
class CRUDServiceImpl<E extends BaseEntity, R extends CommonRepository<E>> implements CRUDService<E> {

    protected final R repository;

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
