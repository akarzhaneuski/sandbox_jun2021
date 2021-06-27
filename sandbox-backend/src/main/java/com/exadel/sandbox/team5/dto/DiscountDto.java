package com.exadel.sandbox.team5.dto;

import com.exadel.sandbox.team5.entity.Location;
import com.exadel.sandbox.team5.entity.Tag;
import lombok.*;

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
    private Set<Tag> tags;
    private CompanyDto company;
    private Double rate;
    private Location location;
}
