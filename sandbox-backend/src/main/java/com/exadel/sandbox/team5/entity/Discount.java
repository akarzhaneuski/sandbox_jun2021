package com.exadel.sandbox.team5.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "discount")
public class Discount extends AuditableEntity {

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "discount_tag",
            joinColumns = { @JoinColumn(name = "discountId") },
            inverseJoinColumns = { @JoinColumn(name = "tagId") }
    )
    Set<Tag> tags = new HashSet<>();

    @ManyToMany(mappedBy = "discounts")
    private Set<Employee> employee = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="companyId", nullable=false)
    private Company company;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "periodStart", nullable = false)
    private Date periodStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "periodEnd", nullable = false)
    private Date periodEnd;

    @Column(name = "quantity", columnDefinition = "int default 10")
    private int quantity;

    @Column(name = "promocode", nullable = false, length = 50)
    private String promoCode;

}
