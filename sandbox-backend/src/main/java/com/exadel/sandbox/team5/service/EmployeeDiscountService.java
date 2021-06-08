package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.EmployeeDiscountEntity;

import java.util.List;

public interface EmployeeDiscountService {

    EmployeeDiscountEntity getById(int id);

    List<EmployeeDiscountEntity> getAll();

    EmployeeDiscountEntity save(EmployeeDiscountEntity employeeDiscount);

    EmployeeDiscountEntity update(EmployeeDiscountEntity employeeDiscount);

    void delete(int id);
}
