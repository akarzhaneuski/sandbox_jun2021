package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.DiscountTagEntity;

import java.util.Set;

public interface DiscountTagService {

    DiscountTagEntity addDiscountTag(DiscountTagEntity discountTag);
    void delete(int id);
    DiscountTagEntity getByName(String name);
    DiscountTagEntity editDiscountTag(DiscountTagEntity discountTag);
    Set<DiscountTagEntity> getAll();
}
