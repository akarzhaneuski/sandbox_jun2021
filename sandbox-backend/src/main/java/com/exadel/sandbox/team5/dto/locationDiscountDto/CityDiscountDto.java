package com.exadel.sandbox.team5.dto.locationDiscountDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CityDiscountDto {
    private Long id;
    private String name;
    private List<AddressDiscountDto> addresses;

    public CityDiscountDto() {
        addresses = new ArrayList<>();
    }

    public void setAddresses(AddressDiscountDto address) {
        addresses.add(address);
    }
}
