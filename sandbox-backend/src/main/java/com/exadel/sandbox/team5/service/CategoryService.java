package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.CategoryDto;
import com.exadel.sandbox.team5.util.CategoryWithTagWithoutIdCategory;

public interface CategoryService extends CRUDService<CategoryDto>{

    CategoryDto save (CategoryWithTagWithoutIdCategory CategoryWithTagWithoutIdCategory);

    CategoryDto save(String category);
}
