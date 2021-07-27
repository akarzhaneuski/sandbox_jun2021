package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagDto extends IdentifierDto {

    @Size(min = 1, max = 50, message = " has to be between {min} and {max} symbols")
    private String name;

    @PositiveOrZero(message = " has to be 0 or positive")
    private Long categoryId;
}
