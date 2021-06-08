package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.TagRepository;
import com.exadel.sandbox.team5.entity.TagEntity;
import com.exadel.sandbox.team5.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Override
    public TagEntity getById(int id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public List<TagEntity> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public TagEntity save(TagEntity tag) {
        return tagRepository.saveAndFlush(tag);
    }

    @Override
    public TagEntity update(TagEntity tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(int id) {
        tagRepository.deleteById(id);
    }
}
