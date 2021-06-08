package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DiscountRepository;
import com.exadel.sandbox.team5.entity.DiscountEntity;
import com.exadel.sandbox.team5.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    @Override
    public DiscountEntity getById(int id) {
        return discountRepository.findById(id).orElse(null);
    }

    @Override
    public List<DiscountEntity> getAll() {
        return discountRepository.findAll();
    }

    @Override
    public DiscountEntity save(DiscountEntity discountEntity) {
        return discountRepository.saveAndFlush(discountEntity);
    }

    @Override
    public DiscountEntity update(DiscountEntity discountEntity) {
        return discountRepository.save(discountEntity);
    }

    @Override
    public void delete(int id) {
        discountRepository.deleteById(id);
    }
}
