package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.util.Filter;

import java.util.List;

public interface DiscountService extends CRUDService<DiscountDto> {
    List<Discount> searchDiscount(final List<Filter> params);
}
