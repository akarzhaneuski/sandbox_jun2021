package com.exadel.sandbox.team5.util;

import lombok.Getter;
import java.util.Set;

@Getter
public class DiscountSearchCriteria extends SearchCriteria {
    private Set<String> tags;
    private String sortBy;
    private String searchText;

    private DiscountSearchCriteria() {
        super();
    }

    public DiscountSearchCriteria(int pageNum, int numPerPage, String direction, String properties, Set<String> tags,
                                  String sortBy, String searchText) {
        super(pageNum, numPerPage, direction, properties);
        this.tags = tags;
        this.sortBy = sortBy;
        this.searchText = searchText;
    }
}
