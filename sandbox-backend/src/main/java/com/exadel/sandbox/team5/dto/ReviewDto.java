package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReviewDto extends IdentifierDto {

    private Integer rate;
    private String comment;
    private Date date;
    private DiscountDto discount;
    private EmployeeDto employee;
}
