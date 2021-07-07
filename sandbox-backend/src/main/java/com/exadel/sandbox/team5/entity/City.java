package com.exadel.sandbox.team5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "city")
public class City extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city")
    private Set<Address> addresses;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "countryId", referencedColumnName = "id")
    private Country country;
}
