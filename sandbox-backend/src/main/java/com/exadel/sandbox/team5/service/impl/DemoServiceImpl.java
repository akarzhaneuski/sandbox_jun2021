package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DemoDAO;
import com.exadel.sandbox.team5.entity.DemoEntity;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl extends CRUDServiceImpl<DemoEntity> {

    public DemoServiceImpl(DemoDAO repository) {
        super(repository);
    }
}
