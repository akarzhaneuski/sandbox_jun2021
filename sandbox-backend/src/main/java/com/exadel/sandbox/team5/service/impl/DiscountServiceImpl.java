package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DiscountRepository;
import com.exadel.sandbox.team5.entity.DiscountEntity;
import com.exadel.sandbox.team5.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public DiscountEntity addDiscount(DiscountEntity discount) {

        DiscountEntity savedDiscount = discountRepository.saveAllAndFlush(discount);
        return savedDiscount;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public DiscountEntity getByName(String name) {
        return null;
    }

    @Override
    public DiscountEntity editDiscount(DiscountEntity discount) {
        return null;
    }

    @Override
    public Set<DiscountEntity> getAll() {
        return null;
    }
}
