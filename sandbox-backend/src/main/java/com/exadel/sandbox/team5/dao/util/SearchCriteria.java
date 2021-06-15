package com.exadel.sandbox.team5.dao.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;

public class SearchCriteria {
    private int page;
    private int size;
    private String properties;

    private SearchCriteria(){}

    public SearchCriteria(int page, int size, String properties) {
        this.page = page;
        this.size = size;
        this.properties = properties;
    }

    public SearchResult findByAsc(){
        PageRequest page = PageRequest.of(this.page, this.size, Sort.Direction.ASC, this.properties);
        return new SearchResult(page.getPageSize(), page);
    }

    public SearchResult findByDesc(){
        PageRequest page = PageRequest.of(this.page, this.size, Sort.Direction.DESC, this.properties);
        return new SearchResult(page.getPageSize(), page);
    }
}
