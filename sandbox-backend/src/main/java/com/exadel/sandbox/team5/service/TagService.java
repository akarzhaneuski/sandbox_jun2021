package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.DiscountTagEntity;
import com.exadel.sandbox.team5.entity.TagEntity;

import java.util.Set;

public interface TagService {

    TagEntity addTag(TagEntity tag);
    void delete(int id);
    TagEntity getByName(String name);
    TagEntity editTag(TagEntity tag);
    Set<TagEntity> getAll();
}
