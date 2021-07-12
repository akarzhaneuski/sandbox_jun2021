package com.exadel.sandbox.team5.dto.search;

import com.exadel.sandbox.team5.util.SearchCriteria;
import com.exadel.sandbox.team5.util.Sorting;
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
    private Set<String> companies;

    public DiscountSearchCriteria(int pageNum, int itemsPerPage, List<Sorting> orders, Set<String> tags,
                                  int rate, String searchText, @Nullable LocationSearchCriteria locationCriteria,
                                  Set<String> companies) {
        super(pageNum, itemsPerPage, orders);
        this.tags = tags;
        this.rate = rate;
        this.searchText = searchText;
        this.locationCriteria = locationCriteria;
        this.companies = companies;
    }

    public boolean isEmpty() {
        return searchText == null && tags == null && locationCriteria == null;
    }
}
