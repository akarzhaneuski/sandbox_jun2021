package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.EmployeeDAO;
import com.exadel.sandbox.team5.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends CRUDServiceImpl<Employee> {

    public EmployeeServiceImpl(EmployeeDAO repository) {
        super(repository);
    }
}
