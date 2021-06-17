package com.exadel.sandbox.team5.dto;

import com.exadel.sandbox.team5.entity.BaseEntity;
import com.exadel.sandbox.team5.entity.Tag;
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
public class DiscountDto extends BaseEntity {

    private String name;
    private String description;
    private Date periodEnd;
    private int quantity;
    private String promoCode;
    private Set<Tag> tags;
    private Set<CompanyDto> companies;
}
