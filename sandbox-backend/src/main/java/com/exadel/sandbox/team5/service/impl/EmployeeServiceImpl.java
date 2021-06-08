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
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;
    @Override
    public Employee getById(Long id) {
        return employeeDAO.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeDAO.deleteById(id);
    }
}
