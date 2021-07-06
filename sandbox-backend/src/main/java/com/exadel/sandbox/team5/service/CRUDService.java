package com.exadel.sandbox.team5.service;

import java.util.List;

public interface CRUDService<T> {

    T getById(Long id) throws Throwable;

    List<T> getAll();

    T save(T employee);

    T update(T employee);

    void delete(Long id);
}
