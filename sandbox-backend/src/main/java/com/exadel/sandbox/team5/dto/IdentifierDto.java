package com.exadel.sandbox.team5.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
public class IdentifierDto {

    @PositiveOrZero(message = " value has to be 0 or positive")
    private Long id;
}
