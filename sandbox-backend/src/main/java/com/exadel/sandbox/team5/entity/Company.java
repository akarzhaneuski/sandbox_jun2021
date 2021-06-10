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

    @ManyToMany(mappedBy = "companies")
    private Set<Location> locations = new HashSet<>();
}
