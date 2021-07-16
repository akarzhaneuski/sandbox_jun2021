package com.exadel.sandbox.team5.config.security.util;

import com.exadel.sandbox.team5.entity.BaseEntity;
import com.exadel.sandbox.team5.entity.City;
import com.exadel.sandbox.team5.entity.Employee;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(Employee employee) {
        return new JwtUser(
                employee.getId(),
                employee.getLogin(),
                employee.getPassword(),
                getCities(employee.getCountry().getCities()),
                mapToGrantedAuthority(employee.getRole().name())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(String role) {
        return Stream.of(new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

    private static long getCities(Set<City> cities) {
        cities.forEach(System.out::println);
        return cities.stream().map(BaseEntity::getId).collect(Collectors.toList()).get(0);
    }
}
