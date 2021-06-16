package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.EmployeeDiscountDAO;
import com.exadel.sandbox.team5.entity.EmployeeDiscount;
import com.exadel.sandbox.team5.service.EmployeeDiscountService;
import com.exadel.sandbox.team5.service.GeneratePromoCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeDiscountImpl implements EmployeeDiscountService {

    private final EmployeeDiscountDAO employeeDiscountDAO;
    private final GeneratePromoCode generatePromoCode;

    @Override
    public EmployeeDiscount getById(Long id) {
        return employeeDiscountDAO.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeDiscount> getAll() {
        return employeeDiscountDAO.findAll();
    }

    @Override
    public EmployeeDiscount save(EmployeeDiscount employeeDiscount) {
        return employeeDiscountDAO.save(employeeDiscount);
    }

    @Override
    public EmployeeDiscount update(EmployeeDiscount employeeDiscount) {
        return employeeDiscountDAO.save(employeeDiscount);
    }

    @Override
    public void delete(Long id) {
        employeeDiscountDAO.deleteById(id);
    }

    @Override
    public EmployeeDiscount saveOrder(Long discountId) {
        System.out.println(generatePromoCode.UUIDgenerator());
        return null;
    }
}
