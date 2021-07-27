package com.exadel.sandbox.team5.dto.locationDiscountDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDiscountDto {

    @PositiveOrZero(message = " has to be 0 or positive")
    private Long id;

    @Size(max = 100, message = " has to be less than {max} symbols")
    private String address;
}
