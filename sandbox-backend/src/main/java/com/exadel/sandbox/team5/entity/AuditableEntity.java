package com.exadel.sandbox.team5.entity;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "id")
    private Long id;

    @Column(name = "modified")
    @LastModifiedDate
    private Date modified;

    @Column(name = "modifiedBy")
    @LastModifiedBy
    private String modifiedBy;
}
