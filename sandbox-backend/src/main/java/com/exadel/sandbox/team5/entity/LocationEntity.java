package com.exadel.sandbox.team5.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name ="location")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @LastModifiedDate
    @Column(name = "modified")
    private Date modified;

    @LastModifiedBy
    @Column(name = "modifiedBy")
    private String modifiedBy;

//    @ManyToMany
//    @JoinTable(
//            name="company_location",
//            joinColumns = @JoinColumn (name = "locationId"),
//            inverseJoinColumns = @JoinColumn(name = "companyId"))
//    Set<CompanyEntity> locationsOfCompany;
}
