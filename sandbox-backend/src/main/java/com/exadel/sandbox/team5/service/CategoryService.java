package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.CategoryDto;
import com.exadel.sandbox.team5.util.CategoryWithTagsDto;

public interface CategoryService extends CRUDService<CategoryDto>{

    CategoryDto save (CategoryWithTagsDto CategoryWithTagsDto);

    CategoryDto save(String category);
}
