package com.exadel.sandbox.team5.util;


import com.amazonaws.util.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CompanySearchCriteria extends SearchCriteria {
    private String searchText;

    public CompanySearchCriteria(int pageNum, int itemsPerPage, List<Sorting> orders, String searchText) {
        super(pageNum, itemsPerPage, orders);
        this.searchText = searchText;
    }

    public boolean isEmpty() {
        return StringUtils.isNullOrEmpty(searchText);
    }
}
