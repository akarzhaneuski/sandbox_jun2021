package com.exadel.sandbox.team5.service;

import java.util.List;

import com.exadel.sandbox.team5.entity.DemoEntity;

public interface DemoService {

    DemoEntity getById(Long id);

    List<DemoEntity> getAll();

    DemoEntity save(DemoEntity demoEntity);

    DemoEntity update(DemoEntity demoEntity);

    void delete(Long id);
}
