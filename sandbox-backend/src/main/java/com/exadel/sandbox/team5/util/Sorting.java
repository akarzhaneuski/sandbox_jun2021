package com.exadel.sandbox.team5.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class Sorting {
    private Sort.Direction direction;
    private String sortBy;

    public Sorting(String direction, String sortBy) {
        this.direction = Sort.Direction.valueOf(direction);
        this.sortBy = sortBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sorting sorting = (Sorting) o;
        return Objects.equals(sortBy, sorting.sortBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortBy);
    }
}
