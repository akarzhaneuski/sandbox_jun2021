package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.BaseEntity;

import java.util.List;

public interface CRUDService<T extends BaseEntity> {

    T getById(Long id);

    List<T> getAll();

    T save(T employee);

    T update(T employee);

    void delete(Long id);

    List<T> getListEntityByOtherEntityId(Long id);
}
