package com.exadel.sandbox.team5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "location")
public class Location extends AuditableEntity {

    @Column(name = "city")
    private String city;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "company_location",
            joinColumns = @JoinColumn(name = "locationId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "companyId", referencedColumnName = "id"))
    private Set<Company> companies = new HashSet<>();
}
