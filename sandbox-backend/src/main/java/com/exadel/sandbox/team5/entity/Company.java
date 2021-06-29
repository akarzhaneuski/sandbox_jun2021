package com.exadel.sandbox.team5.entity;

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
@Table(name = "company")
public class Company extends AuditableEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="countryId")
    private Country country;
}
