package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    Employee getByLogin(String login);
}
