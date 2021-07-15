package com.exadel.sandbox.team5.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthorizationUserDto {
    private String jwt;
    private long location;
    Collection<? extends GrantedAuthority> role;

    public AuthorizationUserDto(String jwt, long location, Collection<? extends GrantedAuthority> role) {
        this.jwt = jwt;
        this.location = location;
        this.role = role;
    }
}
