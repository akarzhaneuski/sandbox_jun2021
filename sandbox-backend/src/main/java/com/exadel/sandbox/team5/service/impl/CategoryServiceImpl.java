package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.CategoryDAO;
import com.exadel.sandbox.team5.dao.TagDAO;
import com.exadel.sandbox.team5.dto.CategoryDto;
import com.exadel.sandbox.team5.dto.CategoryDtoWithTagDtoWithoutIdCategory;
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

    private final TagServiceImpl tagService;
    private final TagDAO tagDAO;

    public CategoryServiceImpl(CategoryDAO entityDao, MapperConverter mapper, TagServiceImpl tagService, TagDAO tagDAO) {
        super(entityDao, Category.class, CategoryDto.class, mapper);
        this.tagService = tagService;
        this.tagDAO = tagDAO;
    }

    @Override
    public CategoryDto save(CategoryDtoWithTagDtoWithoutIdCategory categoryDtoWithTagDtoWithoutIdCategory) {

        Set<TagDto> tagDTOSet = new HashSet<>();

        Set<TagDtoWithoutIdCategory> tagSet = categoryDtoWithTagDtoWithoutIdCategory.getTags();
        String categoryName = categoryDtoWithTagDtoWithoutIdCategory.getName();
        var categoryDto = new CategoryDto();
        categoryDto.setName(categoryName);
        entityDao.saveAndFlush(mapper.map(categoryDto, entityClass));

        Long categoryId = entityDao.findCategoryIdByName(categoryName);
        categoryDto.setId(categoryId);

        for (TagDtoWithoutIdCategory tag : tagSet) {
            var tagDto = new TagDto();
            tagDto.setName(tag.getName());
            tagDto.setCategoryId(categoryId);
            tagService.save(tagDto);
            tagDto.setId(tagDAO.findTagIdByName(tag.getName()));
            tagDTOSet.add(tagDto);
        }

        categoryDto.setTags(tagDTOSet);
        return categoryDto;
    }

    @Override
    public CategoryDto save(String category) {
        var categoryDto = new CategoryDto();
        categoryDto.setName(category);
        entityDao.saveAndFlush(mapper.map(categoryDto, entityClass));
        categoryDto.setId(entityDao.findCategoryIdByName(category));
        return categoryDto;
    }
}
