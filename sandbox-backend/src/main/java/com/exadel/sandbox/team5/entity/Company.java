package com.exadel.sandbox.team5.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Entity
@Table(name = "company")
public class Company extends AuditableEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "company_country",
            joinColumns = @JoinColumn(name = "companyId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "countryId", referencedColumnName = "id"))
    private Set<Country> countries = new HashSet<>();

    @Column(name = "imageId")
    private Long imageId;
}
