package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CategoryDAO;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.EmployeeDAO;
import com.exadel.sandbox.team5.dto.EmployeeDto;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Service
public class EmployeeServiceImpl extends CRUDServiceDtoImpl<EmployeeDAO, Employee, EmployeeDto> implements EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final CategoryDAO categoryDAO;
    private final DiscountDAO discountDAO;

    public EmployeeServiceImpl(EmployeeDAO repository, MapperConverter mapper, EmployeeDAO employeeDAO,
                               CategoryDAO categoryDAO, DiscountDAO discountDAO) {
        super(repository, Employee.class, EmployeeDto.class, mapper);
        this.employeeDAO = employeeDAO;
        this.categoryDAO = categoryDAO;
        this.discountDAO = discountDAO;
    }

    @Override
    public Employee getByLogin(String login) {
        return employeeDAO.getByLogin(login);
    }

    @Override
    public void addFavorites(Long employeeId, Set<Long> discountIds) {
        var employee = employeeDAO.getById(employeeId);
        employee.getFavorites().addAll(discountDAO.getDiscountsByIdIsIn(discountIds));
        employeeDAO.save(employee);
    }

    @Override
    public void removeFavorites(Long employeeId, Set<Long> discountIds) {
        var employee = employeeDAO.getById(employeeId);
        employee.getFavorites().removeAll(discountDAO.getDiscountsByIdIsIn(discountIds));
        employeeDAO.save(employee);
    }

    @Override
    public void addSubscriptions(Long employeeId, Set<Long> categoryIds) {
        var employee = employeeDAO.getById(employeeId);
        employee.getSubscriptions().addAll(categoryDAO.getCategoryByIdIsIn(categoryIds));
        employeeDAO.save(employee);
    }

    @Override
    public void removeSubscriptions(Long employeeId, Set<Long> categoryIds) {
        var employee = employeeDAO.getById(employeeId);
        employee.getSubscriptions().removeAll(categoryDAO.getCategoryByIdIsIn(categoryIds));
        employeeDAO.save(employee);
    }
}
