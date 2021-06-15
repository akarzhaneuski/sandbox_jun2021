package com.exadel.sandbox.team5.configs.security.util;

import com.exadel.sandbox.team5.entity.Employee;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(Employee employee) {
        return new JwtUser(
                employee.getId(),
                employee.getLogin(),
                employee.getPassword(),
                employee.getLocationId(),
                mapToGrantedAuthority(employee.getRole().name())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(String role) {
        return Stream.of(new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }
}
