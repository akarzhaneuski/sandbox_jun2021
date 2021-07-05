package com.exadel.sandbox.team5.util;

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
    private Set<String> addresses;
}
