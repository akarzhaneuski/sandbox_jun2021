package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.search.DiscountSearchCriteria;
import org.springframework.data.domain.Page;
import com.exadel.sandbox.team5.util.DiscountSearchCriteria;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;

import java.util.Map;

public interface DiscountService extends CRUDService<DiscountDto> {

    Map<String, String> getViewsByDiscounts();

    void incrementViews(Long discountId);
    ResultPage<DiscountDto> getByCriteria(DiscountSearchCriteria searchCriteria);

    ResultPage<DiscountDto> getAllSort(SearchCriteria criteria);
}
