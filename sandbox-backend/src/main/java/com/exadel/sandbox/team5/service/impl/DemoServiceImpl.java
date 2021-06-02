package com.exadel.sandbox.team5.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.sandbox.team5.dao.DemoDAO;
import com.exadel.sandbox.team5.entity.DemoEntity;
import com.exadel.sandbox.team5.service.DemoService;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {

    private final DemoDAO dao;

    @Override
    public DemoEntity getById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<DemoEntity> getAll() {
        return dao.findAll();
    }

    @Override
    public DemoEntity save(DemoEntity demoEntity) {
        return dao.save(demoEntity);
    }

    @Override
    public DemoEntity update(DemoEntity demoEntity) {
        return dao.save(demoEntity);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }

}
