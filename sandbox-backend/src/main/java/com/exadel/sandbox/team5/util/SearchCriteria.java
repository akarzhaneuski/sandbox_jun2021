package com.exadel.sandbox.team5.util;

import org.springframework.data.domain.PageRequest;

public class SearchCriteria {
    private PageRequest pageRequest;

    private SearchCriteria() {
    }

    public SearchCriteria(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }
}

