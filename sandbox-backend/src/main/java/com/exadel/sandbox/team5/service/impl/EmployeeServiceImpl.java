package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.EmployeeDAO;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EmployeeServiceImpl extends AbstractService<Employee, EmployeeDAO> implements EmployeeService {

    public EmployeeServiceImpl(EmployeeDAO repository) {
        super(repository);
    }
}
