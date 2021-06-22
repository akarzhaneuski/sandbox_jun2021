package com.exadel.sandbox.team5.util;

import com.exadel.sandbox.team5.dto.CriteriaDto;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class DiscountSearchCriteria extends SearchCriteria {
    private Set<String> tags;
    private int rate;
    private String searchText;

    private DiscountSearchCriteria() {
        super();
    }

    public DiscountSearchCriteria(int pageNum, int itemsPerPage, List<CriteriaDto> orders, Set<String> tags,
                                  int rate, String searchText) {
        super(pageNum, itemsPerPage, orders);
        this.tags = tags;
        this.rate = rate;
        this.searchText = searchText;
    }
}
