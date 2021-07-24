package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.search.DiscountSearchCriteria;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface DiscountService extends CRUDService<DiscountDto> {

    Map<String, String> getViewsByDiscounts();

    void incrementViews(Long discountId);

    ResultPage<DiscountDto> getByCriteria(DiscountSearchCriteria searchCriteria);

    ResultPage<DiscountDto> getAllByCriteria(SearchCriteria criteria);

    ResultPage<DiscountDto> mapDto(Page<Discount> discounts);
}
