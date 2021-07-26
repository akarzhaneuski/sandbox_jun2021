package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CountryDto extends IdentifierDto {

    @Size(max = 100, message = " has to be less than 100 symbols")
    @NotEmpty(message = " has to be not empty")
    private String name;
}
