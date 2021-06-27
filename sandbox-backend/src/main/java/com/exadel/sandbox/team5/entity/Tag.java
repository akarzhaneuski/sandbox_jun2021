package com.exadel.sandbox.team5.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "tag")
    private Set<SubTag> subTags;
}
