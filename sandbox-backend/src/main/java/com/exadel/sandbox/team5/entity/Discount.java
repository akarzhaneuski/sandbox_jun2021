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
public class Discount extends AuditableEntity implements Serializable {

    @ManyToMany
    @JoinTable(
            name = "discount_tag",
            joinColumns = {@JoinColumn(name = "discountId")},
            inverseJoinColumns = {@JoinColumn(name = "tagId")}
    )
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "periodStart")
    private Date periodStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "periodEnd")
    private Date periodEnd;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "promocode")
    private String promoCode;

    @Column(name = "imageId")
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;

    @ManyToMany
    @JoinTable(
            name = "discount_address",
            joinColumns = @JoinColumn(name = "discountId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "addressId", referencedColumnName = "id"))
    private Set<Address> addresses = new HashSet<>();
}
