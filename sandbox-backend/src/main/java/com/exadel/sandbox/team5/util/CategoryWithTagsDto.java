package com.exadel.sandbox.team5.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryWithTagsDto {

    private String name;
    private Set<StringContainer> tags;
}
