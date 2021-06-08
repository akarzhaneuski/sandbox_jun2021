package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.DiscountEntity;

import java.util.Set;

public interface DiscountService {

    DiscountEntity addDiscount(DiscountEntity discount);
    void delete(int id);
    DiscountEntity getByName(String name);
    DiscountEntity editDiscount(DiscountEntity discount);
    Set<DiscountEntity> getAll();
}
