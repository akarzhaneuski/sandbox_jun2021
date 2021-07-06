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
@Table(name = "`order`")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "discountId", referencedColumnName = "id")
    private Discount discount;

    @Column(name = "employeePromocode")
    private String employeePromocode;

    @Column(name = "promoCodeStatus")
    private Boolean promoCodeStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "promoCodePeriodStart")
    private Date promoCodePeriodStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "promoCodePeriodEnd")
    private Date promoCodePeriodEnd;
}
