package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderDto extends IdentifierDto {

    private @Valid EmployeeDto employee;

    private @Valid DiscountDto discount;

    @Size(max = 255, message = " has to be less than 255 symbols")
    @NotBlank(message = " has to be not null or empty")
    private String employeePromocode;

    @NotNull(message = " has to be not null")
    private boolean promoCodeStatus;

    @NotNull(message = "  has to be not null")
    private Date promoCodePeriodStart;

    @NotNull(message = " has to be not null")
    @Future(message = " has to be a date in the future")
    private Date promoCodePeriodEnd;

}
