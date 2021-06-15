package com.exadel.sandbox.team5.dao.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public class SearchCriteria<T> {
    private int page;
    private int size;
    private String[] parameters;
    private JpaRepository repository;

    private SearchCriteria() {
    }

    public SearchCriteria(JpaRepository repository, int page, int size, String... parameters) {
        this.repository = repository;
        this.page = page;
        this.size = size;
        this.parameters = parameters;
    }

    public SearchResult<T> findByAsc() {
        PageRequest pageRequest = PageRequest.of(this.page, this.size, Sort.Direction.ASC, this.parameters);
        Page<T> page = repository.findAll(pageRequest);
        return new SearchResult<T>(page.getNumberOfElements(), page.getContent());
    }

    public SearchResult<T> findByDesc() {
        PageRequest pageRequest = PageRequest.of(this.page, this.size, Sort.Direction.DESC, this.parameters);
        Page<T> page = repository.findAll(pageRequest);
        return new SearchResult<T>(page.getNumberOfElements(), page.getContent());
    }
}
