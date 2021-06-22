package com.exadel.sandbox.team5.util;

import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class DiscountSearchCriteria extends SearchCriteria {
    private final Set<String> tags;
    private final int rate;
    private final String searchText;

    public DiscountSearchCriteria(int pageNum, int itemsPerPage, List<Sorting> orders, Set<String> tags,
                                  int rate, String searchText) {
        super(pageNum, itemsPerPage, orders);
        this.tags = tags;
        this.rate = rate;
        this.searchText = searchText;
    }
}
