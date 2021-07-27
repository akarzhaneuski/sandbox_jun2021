package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends IdentifierDto {

    @Size(max = 50, message = " has to be less than {max} symbols")
    private String name;

    private Set<@Valid TagDto> tags;
}
