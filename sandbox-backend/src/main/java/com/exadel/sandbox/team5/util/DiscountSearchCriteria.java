package com.exadel.sandbox.team5.util;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Getter
public class DiscountSearchCriteria extends SearchCriteria {
    private final List<String> tags;
    private final String selectInput;
    private final String searchText;

    public DiscountSearchCriteria(PageRequest pageRequest, List<String> tags, String selectInput, String searchText) {
        super(pageRequest);
        this.tags = tags;
        this.selectInput = selectInput;
        this.searchText = searchText;
    }
}
