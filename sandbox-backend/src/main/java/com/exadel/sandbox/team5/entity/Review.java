package com.exadel.sandbox.team5.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component

@Entity
@Table(name = "review")
public class Review extends BaseEntity {

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "discountId", referencedColumnName = "id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id")
    private Employee employee;
}
