package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.util.DiscountSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface DiscountService extends CRUDService<DiscountDto> {

    Page<DiscountDto> getByCriteria(DiscountSearchCriteria searchCriteria);

    Long getViewsByDiscountId(Long discountId);

    Map<String, String> getAllViewsByDiscount();
}
