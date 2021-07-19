package com.exadel.sandbox.team5.dto;

import com.exadel.sandbox.team5.entity.Address;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DiscountDto extends IdentifierDto {

    private String name;
    private String description;
    private Date periodStart;
    private Date periodEnd;
    private int quantity;
    private String promoCode;
    private Set<TagDto> tags;
    private CompanyDto company;
    private Double rate;
    private CategoryDto category;
    private String nameImage;
    private Set<Address> addresses;
    private Long views;
}
