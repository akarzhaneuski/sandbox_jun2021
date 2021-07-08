package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.EmployeeDAO;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl extends CRUDServiceImpl<Employee, EmployeeDAO> implements EmployeeService {

    public EmployeeServiceImpl(EmployeeDAO repository) {
        super(repository);
    }

    @Override
    public Employee getByLogin(String login) {
        return repository.getByLogin(login);
    }

}
