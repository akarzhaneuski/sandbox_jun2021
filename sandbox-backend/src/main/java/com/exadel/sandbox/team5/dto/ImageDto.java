package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.InputStream;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImageDto extends IdentifierDto {

    @Size(max = 500, message = " has to be less than 500 symbols")
    private String name;

    @Size(max = 500, message = " has to be less than 500 symbols")
    private InputStream content;

    @NotNull(message = " has to be not null")
    @Size(max = 100, message = " has to be less than 100 symbols")
    private String contentType;
}
