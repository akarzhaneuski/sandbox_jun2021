package com.exadel.sandbox.team5.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tag")
public class Tag extends BaseEntity {

    @Column(name = "tagName")
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "discount_tag",
            joinColumns = {@JoinColumn(name = "tagId")},
            inverseJoinColumns = {@JoinColumn(name = "discountId")}
    )
    private Set<Discount> discounts = new HashSet<>();
}
