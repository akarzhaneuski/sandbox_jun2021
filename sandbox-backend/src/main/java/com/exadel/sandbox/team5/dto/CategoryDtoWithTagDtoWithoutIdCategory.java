package com.exadel.sandbox.team5.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDtoWithTagDtoWithoutIdCategory extends IdentifierDto {

    private String name;
    private Set<TagDtoWithoutIdCategory> tags;
}
