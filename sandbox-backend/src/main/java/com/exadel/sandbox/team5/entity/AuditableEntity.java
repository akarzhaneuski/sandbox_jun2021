package com.exadel.sandbox.team5.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity extends BaseEntity {

    @Column(name = "modified")
    @LastModifiedDate
    private Date modified;

    @Column(name = "modifiedBy")
    @LastModifiedBy
    private String modifiedBy;
}
