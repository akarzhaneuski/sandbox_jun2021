package com.exadel.sandbox.team5.dto;

import com.exadel.sandbox.team5.entity.BaseEntity;
import com.exadel.sandbox.team5.entity.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeDto extends BaseEntity {

    private String login;
    private LocationDto location;
    private Role role;

}
