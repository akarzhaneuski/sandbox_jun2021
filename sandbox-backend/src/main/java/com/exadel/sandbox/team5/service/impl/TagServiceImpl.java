package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.TagDAO;
import com.exadel.sandbox.team5.entity.Tag;
import com.exadel.sandbox.team5.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagServiceImpl extends CRUDServiceImpl<Tag, TagDAO> implements TagService{

    public TagServiceImpl(TagDAO repository) {
        super(repository);
    }

}
