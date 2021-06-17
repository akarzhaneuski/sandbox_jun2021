package com.exadel.sandbox.team5.util;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Getter
public class DiscountSearchCriteria extends SearchCriteria {
    private List<String> tags;
    private String selectInput;
    private String searchText;

    private DiscountSearchCriteria() {
    }

    public DiscountSearchCriteria(PageRequest pageRequest, List<String> tags, String selectInput, String searchText) {
        super(pageRequest);
        this.tags = tags;
        this.selectInput = selectInput;
        this.searchText = searchText;
    }
}
