package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.DiscountEntity;
import com.exadel.sandbox.team5.entity.DiscountTagEntity;

import java.util.List;
import java.util.Optional;

public interface DiscountTagService {

    DiscountTagEntity getById(int id);

    List<DiscountTagEntity> getAll();

    DiscountTagEntity save(DiscountTagEntity discountTagEntity);

    DiscountTagEntity update(DiscountTagEntity discountTagEntity);

    void delete(int id);
}
