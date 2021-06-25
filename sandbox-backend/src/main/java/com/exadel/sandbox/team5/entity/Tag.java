package com.exadel.sandbox.team5.entity;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tag")
public class Tag extends BaseEntity {

    @Column(name = "tagName")
    private String name;
}
