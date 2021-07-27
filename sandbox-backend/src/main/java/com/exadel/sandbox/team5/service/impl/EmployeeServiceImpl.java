package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CategoryDAO;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.EmployeeDAO;
import com.exadel.sandbox.team5.dto.CategoryDto;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.EmployeeDto;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.service.EmployeeService;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;
import com.exadel.sandbox.team5.util.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Set;

@Transactional
@Service
public class EmployeeServiceImpl extends CRUDServiceDtoImpl<EmployeeDAO, Employee, EmployeeDto> implements EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final CategoryDAO categoryDAO;
    private final DiscountDAO discountDAO;
    private final DiscountService discountService;

    public EmployeeServiceImpl(EmployeeDAO repository, MapperConverter mapper, EmployeeDAO employeeDAO,
                               CategoryDAO categoryDAO, DiscountDAO discountDAO, DiscountService discountService) {
        super(repository, Employee.class, EmployeeDto.class, mapper);
        this.employeeDAO = employeeDAO;
        this.categoryDAO = categoryDAO;
        this.discountDAO = discountDAO;
        this.discountService = discountService;
    }

    @Override
    public Employee getByLogin(String login) {
        return employeeDAO.getByLogin(login).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void addFavorites(Set<Long> discountIds) {
        var employee = getEmployee();
        employee.getFavorites().addAll(discountDAO.getDiscountsByIdIsIn(discountIds));
        employeeDAO.save(employee);
    }

    @Override
    public void removeFavorites(Set<Long> discountIds) {
        var employee = getEmployee();
        employee.getFavorites().removeAll(discountDAO.getDiscountsByIdIsIn(discountIds));
        employeeDAO.save(employee);
    }

    @Override
    public void addSubscriptions(Set<Long> categoryIds) {
        var employee = getEmployee();
        employee.getSubscriptions().addAll(categoryDAO.getCategoryByIdIsIn(categoryIds));
        employeeDAO.save(employee);
    }

    @Override
    public void removeSubscriptions(Set<Long> categoryIds) {
        var employee = getEmployee();
        employee.getSubscriptions().removeAll(categoryDAO.getCategoryByIdIsIn(categoryIds));
        employeeDAO.save(employee);
    }

    @Override
    public void addFavorite(Long id) {
        var employee = getEmployee();
        employee.getFavorites().add(discountDAO.getById(id));
        employeeDAO.save(employee);
    }

    @Override
    public ResultPage<DiscountDto> getFavorites(SearchCriteria searchCriteria) {
        return discountService.mapDto(employeeDAO.getFavorites(SecurityUtils.getCurrentUsername(), searchCriteria.getPageRequest()));
    }

    @Override
    public boolean deleteFavorite(Long id) {
        var employee = getEmployee();
        employee.getFavorites().remove(discountDAO.getById(id));
        employeeDAO.save(employee);
        return true;
    }

    @Override
    public Set<CategoryDto> getSubscriptions() {
        return mapper.map(getEmployee(), EmployeeDto.class).getSubscriptions();
    }

    private Employee getEmployee() {
        return employeeDAO.getByLogin(SecurityUtils.getCurrentUsername()).orElseThrow(NoSuchElementException::new);
    }
}
