package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompanyDto extends IdentifierDto {

    @NotNull(message = " has to be not null")
    @NotEmpty(message = " has to be not empty")
    @Size(max = 50, message = " has to be less than 50 symbols")
    private String name;

    @Size(max = 500, message = " has to be less than 500 symbols")
    private String nameImage;

    private Set<@Valid AddressDto> addresses;
}
