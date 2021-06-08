package com.exadel.sandbox.team5.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data

@Entity
@Table(name = "discount_tag")
public class DiscountTagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "discountId", nullable = false)
    private int discountId;

    @Column(name = "tagId", nullable = false)
    private int tagId;

}
