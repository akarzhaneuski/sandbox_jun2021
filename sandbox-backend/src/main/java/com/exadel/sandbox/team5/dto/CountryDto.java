package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Component
public class CountryDto extends IdentifierDto {

    private String name;
    private Set<CityDto> cities;
}
