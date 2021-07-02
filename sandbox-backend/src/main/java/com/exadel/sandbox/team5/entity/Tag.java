package com.exadel.sandbox.team5.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;
}
