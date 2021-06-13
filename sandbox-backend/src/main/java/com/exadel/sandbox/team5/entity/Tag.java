package com.exadel.sandbox.team5.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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

    @ManyToMany(mappedBy = "tags")
    private Set<Discount> discounts = new HashSet<>();

    @Column(name = "tagName")
    private String name;
}
