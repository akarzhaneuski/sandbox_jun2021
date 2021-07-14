package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.CategoryDto;
import com.exadel.sandbox.team5.dto.CategoryDtoWithTagDtoWithoutIdCategory;

public interface CategoryService extends CRUDService<CategoryDto>{

    CategoryDto save (CategoryDtoWithTagDtoWithoutIdCategory CategoryDtoWithTagDtoWithoutIdCategory);

    CategoryDto save(String category);
}
