package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.EmployeeDiscountDAO;
import com.exadel.sandbox.team5.entity.EmployeeDiscount;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDiscountImpl extends CRUDServiceImpl<EmployeeDiscount, EmployeeDiscountDAO> {

    public EmployeeDiscountImpl(EmployeeDiscountDAO repository) {
        super(repository);
    }
}
