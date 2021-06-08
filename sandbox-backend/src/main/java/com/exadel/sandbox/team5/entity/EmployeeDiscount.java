package com.exadel.sandbox.team5.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "employee_discount")
public class EmployeeDiscount extends BaseEntity {

//    id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//    employeeId INT NOT NULL,
//    discountId INT NOT NULL,
//    FOREIGN KEY (employeeId) REFERENCES employee (id),
//    FOREIGN KEY (discountId) REFERENCES discount (id)

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employeeId;

    //TODO fix after discount creation
//    @ManyToOne
//    @JoinColumn(name = "discountId")
//    private Discount discountId;
}
