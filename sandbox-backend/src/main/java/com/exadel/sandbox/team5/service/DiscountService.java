package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.DiscountEntity;

import java.util.List;
import java.util.Optional;

public interface DiscountService {

    DiscountEntity getById(int id);

    List<DiscountEntity> getAll();

    DiscountEntity save(DiscountEntity discountEntity);

    DiscountEntity update(DiscountEntity discountEntity);

    void delete(int id);
}
