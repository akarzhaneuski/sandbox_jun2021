package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.TagDAO;
import com.exadel.sandbox.team5.dto.TagDto;
import com.exadel.sandbox.team5.entity.Tag;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagServiceImpl extends CRUDServiceDtoImpl<TagDto, TagDAO, Tag> implements TagService {

    public TagServiceImpl(TagDto tagDto, TagDAO tagDAO, Tag tag, MapperConverter mapper) {
        super(tagDAO, tag, tagDto, mapper);
    }
}
