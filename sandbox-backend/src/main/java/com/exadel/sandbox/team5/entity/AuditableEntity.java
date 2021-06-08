package com.exadel.sandbox.team5.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class AuditableEntity extends BaseEntity {

    @Column(name = "modified")
    @LastModifiedDate
    private Date modified;

    @Column(name = "modifiedBy")
    @LastModifiedBy
    private String modifiedBy;
}
