package com.exadel.sandbox.team5.dao.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public class SearchCriteria<T> {
    private int pageNum;
    private int pageSize;
    private String[] parameters;
    private JpaRepository repository;

    private SearchCriteria() {
    }

    public SearchCriteria(JpaRepository repository, int pageNum, int pageSize, String... parameters) {
        this.repository = repository;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.parameters = parameters;
    }

    public SearchResult<T> findByAsc() {
        PageRequest pageRequest = PageRequest.of(this.pageNum, this.pageSize, Sort.Direction.ASC, this.parameters);
        Page<T> page = repository.findAll(pageRequest);
        return new SearchResult<T>(page.getNumberOfElements(), page.getContent());
    }

    public SearchResult<T> findByDesc() {
        PageRequest pageRequest = PageRequest.of(this.pageNum, this.pageSize, Sort.Direction.DESC, this.parameters);
        Page<T> page = repository.findAll(pageRequest);
        return new SearchResult<T>(page.getNumberOfElements(), page.getContent());
    }
}
