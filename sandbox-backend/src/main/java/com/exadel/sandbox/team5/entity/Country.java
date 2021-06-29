package com.exadel.sandbox.team5.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}