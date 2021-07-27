package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.CategoryDto;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.EmployeeDto;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;

import java.util.Set;


public interface EmployeeService extends CRUDService<EmployeeDto> {

    Employee getByLogin(String login);

    void addFavorites(Set<Long> discountIds);

    void addFavorite(Long id);

    void removeFavorites(Set<Long> discountIds);

    void addSubscriptions(Set<Long> categoryIds);

    void removeSubscriptions(Set<Long> categoryIds);

    ResultPage<DiscountDto> getFavorites(SearchCriteria searchCriteria);

    boolean deleteFavorite(Long id);

    Set<CategoryDto> getSubscriptions();
}
