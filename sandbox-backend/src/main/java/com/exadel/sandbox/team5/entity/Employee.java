package com.exadel.sandbox.team5.entity;

import lombok.*;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

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
}
