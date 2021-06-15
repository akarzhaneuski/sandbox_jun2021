package com.exadel.sandbox.team5.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "company")
public class Company extends AuditableEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "company_location",
            joinColumns = @JoinColumn(name = "companyId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "locationId", referencedColumnName = "id"))
    private Set<Location> locations = new HashSet<>();
}
