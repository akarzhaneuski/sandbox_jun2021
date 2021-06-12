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
public class DemoServiceImpl extends AbstractService<DemoEntity, DemoDAO> {

    public DemoServiceImpl(DemoDAO repository) {
        super(repository);
    }
}
