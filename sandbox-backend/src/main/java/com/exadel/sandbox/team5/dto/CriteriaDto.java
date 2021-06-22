package com.exadel.sandbox.team5.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@NoArgsConstructor
public class CriteriaDto {
    private Sort.Direction direction;
    private String sortBy;

    public CriteriaDto(String direction, String sortBy) {
        this.direction = Sort.Direction.valueOf(direction);
        this.sortBy = sortBy;
    }
}
