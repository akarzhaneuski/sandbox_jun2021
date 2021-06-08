package com.exadel.sandbox.team5.entity;

import lombok.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "review")
public class Review  extends BaseEntity{
//    id          INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
//    rate        INT      NOT NULL,
//    comment     VARCHAR(500),
//    date        DATETIME NOT NULL,
//    discountId INT      NOT NULL,
//    employeeId INT      NOT NULL,
//    FOREIGN KEY (discountId) REFERENCES discount (id),
//    FOREIGN KEY (employeeId) REFERENCES employee (id)

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private Date date;

    //TODO uncomment after discount will be created;
//    @ManyToOne(cascade= CascadeType.ALL)
//    @JoinColumn(name = "discountId")
//    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee Id;
}
