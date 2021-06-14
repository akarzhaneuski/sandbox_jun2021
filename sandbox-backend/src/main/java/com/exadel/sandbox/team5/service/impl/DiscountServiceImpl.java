package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.entity.Discount;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl extends CRUDServiceImpl<Discount, DiscountDAO> {

    public DiscountServiceImpl(DiscountDAO repository) {
        super(repository);
    }
}
