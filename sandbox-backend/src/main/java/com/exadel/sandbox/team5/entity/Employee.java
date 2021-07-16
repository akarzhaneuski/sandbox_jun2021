package com.exadel.sandbox.team5.entity;

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

    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "employee_notification_category",
            joinColumns = {@JoinColumn(name = "employeeId")},
            inverseJoinColumns = {@JoinColumn(name = "categoryId")}
    )
    private Set<Category> subscriptions = new HashSet<>();
}
