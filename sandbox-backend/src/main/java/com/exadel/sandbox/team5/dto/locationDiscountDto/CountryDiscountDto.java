package com.exadel.sandbox.team5.dto.locationDiscountDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CountryDiscountDto {
    private Long id;
    private String name;
    private List<CityDiscountDto> cities;
}
