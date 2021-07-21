package com.exadel.sandbox.team5.service;

import java.util.List;

public interface CRUDService<T> {

    T getById(Long id);

    List<T> getAll();

    T save(T entity);

    T update(T entity);

    void delete(Long id);
}
