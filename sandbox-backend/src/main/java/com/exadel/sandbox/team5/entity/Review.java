package com.exadel.sandbox.team5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    private Integer rate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private Date date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "discountId")
    private Discount discount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
}
