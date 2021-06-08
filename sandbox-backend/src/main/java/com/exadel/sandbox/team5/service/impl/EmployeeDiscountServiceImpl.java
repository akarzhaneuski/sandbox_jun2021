package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.EmployeeDiscountRepository;
import com.exadel.sandbox.team5.entity.EmployeeDiscountEntity;
import com.exadel.sandbox.team5.service.EmployeeDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeDiscountServiceImpl implements EmployeeDiscountService {

    private final EmployeeDiscountRepository employeeDiscountRepository;

    @Override
    public EmployeeDiscountEntity getById(int id) {
        return employeeDiscountRepository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeDiscountEntity> getAll() {
        return employeeDiscountRepository.findAll();
    }

    @Override
    public EmployeeDiscountEntity save(EmployeeDiscountEntity employeeDiscount) {
        return employeeDiscountRepository.saveAndFlush(employeeDiscount);
    }

    @Override
    public EmployeeDiscountEntity update(EmployeeDiscountEntity employeeDiscount) {
        return employeeDiscountRepository.save(employeeDiscount);
    }

    @Override
    public void delete(int id) {
        employeeDiscountRepository.deleteById(id);
    }
}
