package com.exadel.sandbox.team5.dao.util;

import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;

public class SearchResult {
    private int totalCount;
    private PageRequest result;

    private SearchResult(){}

    public SearchResult(int totalCount, PageRequest result) {
        this.totalCount = totalCount;
        this.result = result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public PageRequest getResult() {
        return result;
    }
}
