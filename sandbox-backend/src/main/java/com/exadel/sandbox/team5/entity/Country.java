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
@Table(name = "country")
public class Country extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<City> cities;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;
}
