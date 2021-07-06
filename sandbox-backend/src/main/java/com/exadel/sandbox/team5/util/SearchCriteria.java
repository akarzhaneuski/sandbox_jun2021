package com.exadel.sandbox.team5.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchCriteria {
    private int pageNum;
    private int itemsPerPage;
    private List<Sorting> orders;

    public SearchCriteria(int pageNum, int itemsPerPage, @Nullable List<Sorting> orders) {
        this.pageNum = pageNum;
        this.itemsPerPage = itemsPerPage;
        this.orders = orders;
    }

    public PageRequest getPageRequest() {
        if (orders == null || orders.isEmpty()) return PageRequest.of(pageNum, itemsPerPage);
        List<Sort.Order> result = new ArrayList<>();
        orders.forEach(x -> result.add(new Sort.Order(x.getDirection(), x.getSortBy())));
        return PageRequest.of(pageNum, itemsPerPage, Sort.by(result));
    }
}
