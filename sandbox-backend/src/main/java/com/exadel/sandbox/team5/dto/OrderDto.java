package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Component
public class OrderDto extends IdentifierDto {

    private EmployeeDto employee;
    private DiscountDto discount;
    private String employeePromocode;
    private boolean promoCodeStatus;
    private Date promoCodePeriodStart;
    private Date promoCodePeriodEnd;

}
