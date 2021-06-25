package com.exadel.sandbox.team5.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "image")
public class Image extends BaseEntity {

    @Column(name = "imageURL")
    private String imageURL;
    @Column(name = "contentType")
    private String contentType;
}
