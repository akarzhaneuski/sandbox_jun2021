package com.exadel.sandbox.team5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @Column(name = "name")
    private String address;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private City city;
}
