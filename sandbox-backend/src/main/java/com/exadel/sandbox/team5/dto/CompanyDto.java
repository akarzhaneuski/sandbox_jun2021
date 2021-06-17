package com.exadel.sandbox.team5.dto;

import com.exadel.sandbox.team5.entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompanyDto extends BaseEntity {

    private String name;
    private Set<LocationDto> locations;
}
