package com.exadel.sandbox.team5.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class AuthorizationUserDto {
    private String jwt;
    private long cityId;
    List<GrantedAuthority> role;

    public AuthorizationUserDto(String jwt, long cityId, Collection<? extends GrantedAuthority> role) {
        this.jwt = jwt;
        this.cityId = cityId;
        this.role = new ArrayList<>(role);
    }
}
