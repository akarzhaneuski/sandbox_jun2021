package com.exadel.sandbox.team5.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.sandbox.team5.dao.DemoDAO;
import com.exadel.sandbox.team5.entity.DemoEntity;

@Transactional
@Service
public class DemoServiceImpl extends AbstractService<DemoEntity, DemoDAO> {

    public DemoServiceImpl(DemoDAO repository) {
        super(repository);
    }
}
