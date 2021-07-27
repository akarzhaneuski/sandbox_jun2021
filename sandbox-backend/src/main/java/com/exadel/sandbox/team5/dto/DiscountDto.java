package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DiscountDto extends IdentifierDto {

    @Size(max = 50, message = " has to be less than {max} symbols")
    private String name;

    @Size(max = 255, message = " has to be less than {max} symbols")
    private String description;

    private Date periodStart;

    @NotNull(message = " has to be not null")
    @Future(message = " has to be a date in the future")
    private Date periodEnd;

    @PositiveOrZero(message = " has to be 0 or positive")
    private int quantity;

    @Size(max = 50, message = " has to be less than {max} symbols")
    private String promoCode;

    private Set<@Valid TagDto> tags;

    private @Valid CompanyDto company;

    @PositiveOrZero(message = " has to be 0 or positive")
    private Double rate;

    private @Valid CategoryDto category;

    @Size(max = 500, message = " has to be less than {max} symbols")
    private String nameImage;

    private Set<@Valid AddressDto> addresses;

    @NotNull(message = " value has to be not null")
    @PositiveOrZero(message = " value has to be 0 or positive")
    private Long views;
}
