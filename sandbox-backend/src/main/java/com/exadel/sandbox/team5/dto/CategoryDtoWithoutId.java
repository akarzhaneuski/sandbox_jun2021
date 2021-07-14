package com.exadel.sandbox.team5.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDtoWithoutId {

    private String name;
    private Set<TagDtoWithoutIdCategory> tags;
}
