package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddressDto extends IdentifierDto {

    @Size(max = 100, message = " has to be less than {max} symbols")
    private String address;

    private @Valid CityDto city;
}
