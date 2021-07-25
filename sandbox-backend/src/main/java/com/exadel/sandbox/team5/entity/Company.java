package com.exadel.sandbox.team5.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company extends AuditableEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "imageId")
    private Long imageId;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "company_address",
            joinColumns = @JoinColumn(name = "companyId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "addressId", referencedColumnName = "id"))
    private Set<Address> addresses = new HashSet<>();
}
