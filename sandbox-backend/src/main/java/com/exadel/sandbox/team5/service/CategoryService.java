package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.CategoryDto;
import com.exadel.sandbox.team5.dto.CategoryDtoWithoutId;

public interface CategoryService extends CRUDService<CategoryDto>{

    CategoryDto save (CategoryDtoWithoutId categoryDtoWithoutId);
}
