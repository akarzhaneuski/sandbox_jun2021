package com.exadel.sandbox.team5.dao.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public class SearchCriteria {
    private PageRequest pageRequest;

    private SearchCriteria() {
    }

    public SearchCriteria(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public <T, U> Page<T> find(JpaRepository<T, U> repository) {
        return repository.findAll(pageRequest);
    }


}
