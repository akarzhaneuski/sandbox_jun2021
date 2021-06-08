package com.exadel.sandbox.team5.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "discount_tag")
public class DiscountTag extends BaseEntity {

    @Column(name = "discountId", nullable = false)
    private int discountId;

    @Column(name = "tagId", nullable = false)
    private int tagId;

}
