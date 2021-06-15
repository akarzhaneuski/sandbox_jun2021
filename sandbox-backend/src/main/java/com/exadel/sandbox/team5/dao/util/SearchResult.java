package com.exadel.sandbox.team5.dao.util;

import java.util.List;

public class SearchResult<T> {
    private int totalCount;
    private List<T> results;

    private SearchResult() {
    }

    public SearchResult(int totalCount, List<T> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<T> getResults() {
        return results;
    }
}
