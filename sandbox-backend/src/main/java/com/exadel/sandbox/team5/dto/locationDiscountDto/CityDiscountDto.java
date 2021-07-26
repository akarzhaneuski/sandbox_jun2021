package com.exadel.sandbox.team5.dto.locationDiscountDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CityDiscountDto {

    @PositiveOrZero(message = " has to be 0 or positive")
    private Long id;

    @Size(max = 100, message = " has to be less than 100 symbols")
    private String name;

    private List<@Valid AddressDiscountDto> addresses;

    public CityDiscountDto() {
        addresses = new ArrayList<>();
    }

    public void setAddresses(AddressDiscountDto address) {
        addresses.add(address);
    }
}
