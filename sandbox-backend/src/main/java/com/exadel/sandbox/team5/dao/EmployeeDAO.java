package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {
    Optional<Employee> findByLogin(String login);
    Boolean existsByLogin(String login);
}
