package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.util.DiscountSearchCriteria;
import com.exadel.sandbox.team5.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface DiscountService extends CRUDService<DiscountDto> {

    Page<DiscountDto> getByCriteria(DiscountSearchCriteria searchCriteria);

    Page<DiscountDto> getAllSort(SearchCriteria criteria);
}
