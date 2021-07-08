package com.exadel.sandbox.team5.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationSearchCriteria {

    private String country;
    private Set<String> cities;
}
