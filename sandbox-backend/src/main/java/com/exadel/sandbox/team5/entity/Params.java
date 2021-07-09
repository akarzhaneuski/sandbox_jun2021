package com.exadel.sandbox.team5.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "params",uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Params extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;
}
