package com.exadel.sandbox.team5.dto.search;

import com.amazonaws.util.StringUtils;
import com.exadel.sandbox.team5.util.QueryUtils;
import com.exadel.sandbox.team5.util.SearchCriteria;
import com.exadel.sandbox.team5.util.Sorting;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.*;

@Getter
public class DiscountSearchCriteria extends SearchCriteria {
    private Set<String> tags;
    private double rate;
    private String searchText;
    private LocationSearchCriteria locationCriteria;
    private Set<String> companies;

    public DiscountSearchCriteria(int pageNum, int itemsPerPage, List<Sorting> orders, Set<String> tags,
                                  double rate, String searchText, @Nullable LocationSearchCriteria locationCriteria,
                                  Set<String> companies) {
        super(pageNum, itemsPerPage, orders);
        this.tags = QueryUtils.safeCollectionParam(tags);
        this.rate = rate;
        this.searchText = StringUtils.isNullOrEmpty(searchText)
                ? null
                : QueryUtils.getWildcard(searchText);
        this.locationCriteria = Objects.requireNonNullElse(locationCriteria, new LocationSearchCriteria(null, Collections.emptySet()));
        this.companies = QueryUtils.safeCollectionParam(companies);
    }

    public boolean isEmpty() {
        return searchText == null && tags == null && locationCriteria == null;
    }
}
