package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.EmployeeDto;
import com.exadel.sandbox.team5.entity.Employee;

import java.util.Set;


public interface EmployeeService extends CRUDService<EmployeeDto> {

    Employee getByLogin(String login);

    void addFavorites(Long employeeId, Set<Long> discountIds);

    void removeFavorites(Long employeeId, Set<Long> discountIds);

    void addSubscriptions(Long employeeId, Set<Long> categoryIds);

    void removeSubscriptions(Long employeeId, Set<Long> categoryIds);
}
