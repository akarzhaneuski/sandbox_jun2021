package com.exadel.sandbox.team5.util;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
public class SearchCriteria {
    private int pageNum;
    private int itemsPerPage;
    private String direction;
    private String properties;

    protected SearchCriteria() {
    }

    public SearchCriteria(int pageNum, int itemsPerPage, String direction, String properties) {
        this.pageNum = pageNum;
        this.itemsPerPage = itemsPerPage;
        this.direction = direction;
        this.properties = properties;
    }

    public PageRequest getPageRequest() {
        Sort.Direction d;
        if (direction.equals("ASC")) d = Sort.Direction.ASC;
        else if (direction.equals("DESC")) d = Sort.Direction.DESC;
        else d = Sort.Direction.fromString(direction);
        return PageRequest.of(pageNum, itemsPerPage, d, properties);
    }
}

