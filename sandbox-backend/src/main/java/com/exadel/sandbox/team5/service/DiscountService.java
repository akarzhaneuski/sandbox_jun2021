package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.Discount;

import java.util.List;

public interface DiscountService extends CRUDService<Discount> {
    List<Discount> getByNameOrDescriptionContaining(String searchWord);
}
