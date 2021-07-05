package com.exadel.sandbox.team5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @Column(name = "address")
    private String address;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private City city;

    @ManyToMany
    @JoinTable(
            name = "discount_address",
            joinColumns = @JoinColumn(name = "addressId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "discountId", referencedColumnName = "id"))
    private Set<Discount> discounts = new HashSet<>();
}
