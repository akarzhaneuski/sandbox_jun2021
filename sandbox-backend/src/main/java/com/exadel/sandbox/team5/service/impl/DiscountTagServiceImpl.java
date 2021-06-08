package com.exadel.sandbox.team5.service.impl;


import com.exadel.sandbox.team5.dao.DiscountTagRepository;
import com.exadel.sandbox.team5.entity.DiscountTagEntity;
import com.exadel.sandbox.team5.service.DiscountTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class DiscountTagServiceImpl implements DiscountTagService {

    private final DiscountTagRepository discountTagRepository;

    @Override
    public DiscountTagEntity getById(int id) {
        return discountTagRepository.findById(id).orElse(null);
    }

    @Override
    public List<DiscountTagEntity> getAll() {
        return discountTagRepository.findAll();
    }

    @Override
    public DiscountTagEntity save(DiscountTagEntity discountTagEntity) {
        return discountTagRepository.saveAndFlush(discountTagEntity);
    }

    @Override
    public DiscountTagEntity update(DiscountTagEntity discountTagEntity) {
        return discountTagRepository.save(discountTagEntity);
    }

    @Override
    public void delete(int id) {
        discountTagRepository.deleteById(id);
    }
}
