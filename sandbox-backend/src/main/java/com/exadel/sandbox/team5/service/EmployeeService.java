package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.Employee;


public interface EmployeeService extends CRUDService<Employee> {
    Employee getByLogin(String login);

}
