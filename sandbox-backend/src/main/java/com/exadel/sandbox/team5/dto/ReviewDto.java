package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReviewDto extends IdentifierDto {

    @NotNull(message = " has to be not null")
    @PositiveOrZero(message = " has to be 0 or positive")
    private Integer rate;

    @Size(max = 500, message = " must be less than 500 symbols")
    private String comment;

    @NotNull(message = " has to be not null")
    private Date date;

    private @Valid DiscountDto discount;

    private @Valid EmployeeDto employee;
}
