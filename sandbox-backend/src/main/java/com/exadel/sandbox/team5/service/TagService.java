package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.DiscountTagEntity;
import com.exadel.sandbox.team5.entity.TagEntity;

import java.util.List;

public interface TagService {

    TagEntity getById(int id);

    List<TagEntity> getAll();

    TagEntity save(TagEntity tag);

    TagEntity update(TagEntity tag);

    void delete(int id);
}
