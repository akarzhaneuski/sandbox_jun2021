package com.exadel.sandbox.team5.entity;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "employee_discount")
public class EmployeeDiscount extends BaseEntity{

    @Column(name = "employeeId", nullable = false)
    private long employeeId;

    @Column(name = "discountId", nullable = false)
    private long discountId;

}
