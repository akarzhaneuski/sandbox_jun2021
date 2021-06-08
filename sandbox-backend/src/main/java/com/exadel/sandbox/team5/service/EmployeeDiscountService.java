package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.EmployeeDiscountEntity;

import java.util.Set;

public interface EmployeeDiscountService {

    EmployeeDiscountEntity addDiscount(EmployeeDiscountEntity employeeDiscount);
    void delete(int id);
    EmployeeDiscountEntity getByName(String name);
    EmployeeDiscountEntity editDiscount(EmployeeDiscountEntity employeeDiscount);
    Set<EmployeeDiscountEntity> getAll();
}
