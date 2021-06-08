package com.exadel.sandbox.team5.service.impl;


import com.exadel.sandbox.team5.dao.DiscountTagDAO;
import com.exadel.sandbox.team5.entity.DiscountTag;
import com.exadel.sandbox.team5.service.DiscountTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class DiscountTagServiceImpl implements DiscountTagService {

    private final DiscountTagDAO discountTagDAO;

    @Override
    public DiscountTag getById(Long id) {
        return discountTagDAO.findById(id).orElse(null);
    }

    @Override
    public List<DiscountTag> getAll() {
        return discountTagDAO.findAll();
    }

    @Override
    public DiscountTag save(DiscountTag discountTag) {
        return discountTagDAO.saveAndFlush(discountTag);
    }

    @Override
    public DiscountTag update(DiscountTag discountTag) {
        return discountTagDAO.save(discountTag);
    }

    @Override
    public void delete(Long id) {
        discountTagDAO.deleteById(id);
    }
}
