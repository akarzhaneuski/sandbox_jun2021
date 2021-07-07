package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.util.DiscountSearchCriteria;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;

public interface DiscountService extends CRUDService<DiscountDto> {

    ResultPage<DiscountDto> getByCriteria(DiscountSearchCriteria searchCriteria);

    ResultPage<DiscountDto> getAllSort(SearchCriteria criteria);
}
