package com.exadel.sandbox.team5.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;

@Getter
@AllArgsConstructor
public class SearchCriteria {
    private PageRequest pageRequest;

    private SearchCriteria() {
    }
}

