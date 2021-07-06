package com.exadel.sandbox.team5.entity;


import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@Entity
@Table(name = "tag")
public class Tag extends BaseEntity {

    @Column(name = "tagName")
    private String name;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;
}
