package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.DiscountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DiscountService extends CRUDService<DiscountDto> {
    Page<Discount> getByFilters(SearchCriteria searchCriteria);
}
