package com.exadel.sandbox.team5.util;

import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public class SearchCriteria {
    private PageRequest pageRequest;
    /*private Date date;
    private List<String> properties;
    private long number;*/

    private SearchCriteria() {
    }

    public SearchCriteria(int pageNum, int itemsPerPage, Sort.Direction direction,
                          Date date, List<String> properties, long number) {
        properties.add(date.toString());
        properties.add(""+number);
        this.pageRequest = PageRequest.of(pageNum, itemsPerPage, direction,
                String.valueOf(properties));
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }
}

