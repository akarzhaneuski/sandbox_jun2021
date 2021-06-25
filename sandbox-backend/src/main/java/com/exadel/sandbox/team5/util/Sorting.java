package com.exadel.sandbox.team5.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@NoArgsConstructor
public class Sorting {
    private Sort.Direction direction;
    private String sortBy;

    public Sorting(String direction, String sortBy) {
        this.direction = Sort.Direction.valueOf(direction);
        this.sortBy = sortBy;
    }
}
