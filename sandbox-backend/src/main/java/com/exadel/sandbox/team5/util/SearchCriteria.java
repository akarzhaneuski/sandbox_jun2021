package com.exadel.sandbox.team5.util;

import com.exadel.sandbox.team5.dto.CriteriaDto;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SearchCriteria {
    private int pageNum;
    private int itemsPerPage;
    private List<CriteriaDto> orders;

    protected SearchCriteria() {
    }

    public SearchCriteria(int pageNum, int itemsPerPage, List<CriteriaDto> orders) {
        this.pageNum = pageNum;
        this.itemsPerPage = itemsPerPage;
        this.orders = orders;
    }

    public PageRequest getPageRequest() {
        List<Sort.Order> result = new ArrayList<>();
        orders.forEach(x -> result.add(new Sort.Order(x.getDirection(), x.getSortBy())));
        return PageRequest.of(pageNum, itemsPerPage, Sort.by(result));
    }
}

