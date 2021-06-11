package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Tag;
import com.exadel.sandbox.team5.service.CRUDService;
import com.exadel.sandbox.team5.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountDAO discountDAO;

    @Override
    public Discount getById(Long id) {
        return discountDAO.findById(id).orElse(null);
    }

    @Override
    public List<Discount> getAll() {
        return discountDAO.findAll();
    }

    @Override
    public Page<Discount> getAll(Pageable pageable) {
        return discountDAO.findAll(pageable);
    }

    @Override
    public Page<Discount> getAllByTags(Tag tag, Pageable pageable) {
        return discountDAO.findByTags(tag, pageable);
    }

    @Override
    public Discount save(Discount discount) {
        return discountDAO.saveAndFlush(discount);
    }

    @Override
    public Discount update(Discount discount) {
        return discountDAO.save(discount);
    }

    @Override
    public void delete(Long id) {
        discountDAO.deleteById(id);
    }
}
