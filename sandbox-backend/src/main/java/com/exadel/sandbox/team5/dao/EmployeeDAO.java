package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends CommonRepository<Employee> {

    Employee getByLogin(String login);
}
