package com.exadel.sandbox.team5.dto;

import com.exadel.sandbox.team5.entity.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeDto extends IdentifierDto {

    @NotNull(message = " has to be not null")
    @Size(max = 50, message = " has to be less than {max} symbols")
    private String login;

    private @Valid CountryDto country;

    @NotNull(message = " has to be not null")
    private Role role;

    @Size(max = 320, message = " has to be less than {max} symbols")
    @Email(message = "not valid. Please enter a valid email address (Ex: username@example.com)")
    private String email;

    private Set<@Valid CategoryDto> subscriptions;

    private Set<@Valid DiscountDto> favorites;
}
