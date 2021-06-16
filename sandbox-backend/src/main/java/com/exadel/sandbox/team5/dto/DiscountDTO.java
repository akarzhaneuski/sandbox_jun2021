package com.exadel.sandbox.team5.dto;

import com.exadel.sandbox.team5.entity.BaseEntity;
import com.exadel.sandbox.team5.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DiscountDTO extends BaseEntity {
    
    private String name;
    private String description;
    private Date periodStart;
    private Date periodEnd;
    private int quantity;
    private String promoCode;
    private Set<Tag> tags;
}
