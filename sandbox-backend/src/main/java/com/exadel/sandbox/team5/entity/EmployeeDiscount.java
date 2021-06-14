package com.exadel.sandbox.team5.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "employee_discount")
public class EmployeeDiscount extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "discountId")
    private Discount discount;
}
