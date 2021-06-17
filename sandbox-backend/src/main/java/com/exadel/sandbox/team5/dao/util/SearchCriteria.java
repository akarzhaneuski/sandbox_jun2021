package com.exadel.sandbox.team5.dao.util;

import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

public class SearchCriteria {
    private PageRequest pageRequest;
    private Date date;
    private List<String> properties;
    private long number;

    private SearchCriteria() {
    }

    public SearchCriteria(PageRequest pageRequest, Date date, List<String> properties, long number) {
        this.pageRequest = pageRequest;
        this.date = date;
        this.properties = properties;
        this.number = number;
    }

    public SearchCriteria(PageRequest pageRequest, Date date, List<String> properties) {
        this.pageRequest = pageRequest;
        this.date = date;
        this.properties = properties;
    }

    public SearchCriteria(PageRequest pageRequest, Date date) {
        this.pageRequest = pageRequest;
        this.date = date;
    }

    public SearchCriteria(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public Date getDate() {
        return date;
    }

    public List<String> getProperties() {
        return properties;
    }

    public long getNumber() {
        return number;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }
}
