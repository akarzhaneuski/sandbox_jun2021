package com.exadel.sandbox.team5.entity;

import lombok.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "review")
public class Review extends BaseEntity {

    @Column(name = "rate")
    private int rate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "discountId")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
