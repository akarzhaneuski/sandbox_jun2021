package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.service.CRUDService;
import com.exadel.sandbox.team5.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DiscountServiceImpl extends AbstractService<Discount, DiscountDAO> {

    public DiscountServiceImpl(DiscountDAO repository) {
        super(repository);
    }
}
