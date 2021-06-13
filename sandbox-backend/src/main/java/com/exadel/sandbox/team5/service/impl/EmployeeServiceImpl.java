package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.EmployeeDAO;
import com.exadel.sandbox.team5.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl extends AbstractService<Employee, EmployeeDAO> {

    public EmployeeServiceImpl(EmployeeDAO repository) {
        super(repository);
    }
}
