package com.exadel.sandbox.team5.dao.util;

import org.springframework.data.domain.PageRequest;

import java.util.List;

public class SearchCriteria {
    private PageRequest pageRequest;
    private List<String> properties;

    private SearchCriteria() {
    }

    public SearchCriteria(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public SearchCriteria(PageRequest pageRequest, List<String> properties) {
        this.pageRequest = pageRequest;
        this.properties = properties;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public List<String> getProperties() {
        return properties;
    }
}
