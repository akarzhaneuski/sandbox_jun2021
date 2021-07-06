package com.exadel.sandbox.team5.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class DiscountSearchCriteria extends SearchCriteria {
    private Set<String> tags;
    private int rate;
    private String searchText;
    private LocationSearchCriteria locationCriteria;

    public DiscountSearchCriteria(int pageNum, int itemsPerPage, List<Sorting> orders, Set<String> tags,
                                  int rate, String searchText, @Nullable LocationSearchCriteria locationCriteria) {
        super(pageNum, itemsPerPage, orders);
        this.tags = tags;
        this.rate = rate;
        this.searchText = searchText;
        this.locationCriteria = locationCriteria;
    }
}
