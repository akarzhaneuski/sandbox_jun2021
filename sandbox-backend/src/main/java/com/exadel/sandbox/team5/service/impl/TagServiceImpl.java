package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.TagDAO;
import com.exadel.sandbox.team5.entity.Tag;
import com.exadel.sandbox.team5.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private TagDAO tagDAO;

    @Override
    public Tag getById(Long id) {
        return tagDAO.findById(id).orElse(null);
    }

    @Override
    public List<Tag> getAll() {
        return tagDAO.findAll();
    }

    @Override
    public Tag save(Tag tag) {
        return tagDAO.saveAndFlush(tag);
    }

    @Override
    public Tag update(Tag tag) {
        return tagDAO.save(tag);
    }

    @Override
    public void delete(Long id) {
        tagDAO.deleteById(id);
    }
}
