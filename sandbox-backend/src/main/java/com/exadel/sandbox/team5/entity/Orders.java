package com.exadel.sandbox.team5.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "orders")
public class Orders extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "discountId", referencedColumnName = "id")
    private Discount discount;

    @Column(name = "employeePromocode")
    private String employeePromocode;

    @Column(name = "promoCodeStatus")
    private boolean promoCodeStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "promoCodePeriodStart")
    private Date promoCodePeriodStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "promoCodePeriodEnd")
    private Date promoCodePeriodEnd;
}
