package com.exadel.sandbox.team5.dto.locationDiscountDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CountryDiscountDto {

    @PositiveOrZero(message = " has to be 0 or positive")
    private Long id;

    @Size(max = 100, message = " has to be less than 100 symbols")
    private String name;

    private List<@Valid CityDiscountDto> cities;
}
