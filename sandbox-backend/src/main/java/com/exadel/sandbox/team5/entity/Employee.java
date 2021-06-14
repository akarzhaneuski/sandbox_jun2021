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
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = {"login"}))
public class Employee extends AuditableEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "locationId")
    private Long locationId;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    Set<EmployeeDiscount> employeeDiscounts = new HashSet<>();

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name="location_Id")
//    private Location location;
}
