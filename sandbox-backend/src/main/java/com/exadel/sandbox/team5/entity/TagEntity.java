package com.exadel.sandbox.team5.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tag")
public class TagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "tagName", nullable = false, length = 50)
    private String tagName;
}
