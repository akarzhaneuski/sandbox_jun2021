package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.EmployeeDto;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;

import java.util.Set;


public interface EmployeeService extends CRUDService<EmployeeDto> {

    Employee getByLogin(String login);

    void addFavorites(Long employeeId, Set<Long> discountIds);

    void addFavorite(Long id);

    void removeFavorites(Long employeeId, Set<Long> discountIds);

    void addSubscriptions(Long employeeId, Set<Long> categoryIds);

    void removeSubscriptions(Long employeeId, Set<Long> categoryIds);

    ResultPage<DiscountDto> getFavorites(SearchCriteria searchCriteria);

    boolean deleteFavorite(Long id);
}
