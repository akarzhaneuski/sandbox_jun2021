package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CategoryDAO;
import com.exadel.sandbox.team5.dto.CategoryDto;
import com.exadel.sandbox.team5.dto.CategoryDtoWithoutId;
import com.exadel.sandbox.team5.dto.TagDto;
import com.exadel.sandbox.team5.dto.TagDtoWithoutIdCategory;
import com.exadel.sandbox.team5.entity.Category;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class CategoryServiceImpl extends CRUDServiceDtoImpl<CategoryDAO, Category, CategoryDto> implements CategoryService {

    TagServiceImpl tagService;

    public CategoryServiceImpl(CategoryDAO entityDao, MapperConverter mapper, TagServiceImpl tagService) {
        super(entityDao, Category.class, CategoryDto.class, mapper);
        this.tagService = tagService;
    }

    public CategoryDto save(CategoryDtoWithoutId categoryDtoWithoutId) {

        Set<TagDto> tagDTOSet = new HashSet<>();

        Set<TagDtoWithoutIdCategory> tagSet = categoryDtoWithoutId.getTags();
        String categoryName = categoryDtoWithoutId.getName();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(categoryName);
        entityDao.saveAndFlush(mapper.map(categoryDto, entityClass));

        Long categoryId = entityDao.findCategoryIdByName(categoryName);

        for (TagDtoWithoutIdCategory tag : tagSet) {
            TagDto tagDto = new TagDto();
            tagDto.setName(tag.getName());
            tagDto.setCategoryId(categoryId);
            tagDTOSet.add(tagDto);
            tagService.save(tagDto);
        }

        categoryDto.setTags(tagDTOSet);
        return categoryDto;
    }
}
