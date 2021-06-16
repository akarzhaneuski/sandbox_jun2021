package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dto.DiscountDTO;
import com.exadel.sandbox.team5.mapper.MapperDTO;
import com.exadel.sandbox.team5.mapper.MapperUtil;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountDAO discountDAO;
    private final MapperDTO mapper;

    @Override
    public DiscountDTO getById(Long id) {
        return mapper.mapToDiscountDto(discountDAO.findById(id).orElse(null));
    }

    @Override
    public List<DiscountDTO> getAll() {
        List<Discount> discounts = discountDAO.findAll();
        return MapperUtil.convertList(discounts, mapper::mapToDiscountDto);
    }

    @Override
    public DiscountDTO save(DiscountDTO discount) {
        Discount dis = mapper.mapDtoToDiscount(discount);
        return mapper.mapToDiscountDto(discountDAO.saveAndFlush(dis));
    }

    @Override
    public DiscountDTO update(DiscountDTO discount) {
        Discount dis = mapper.mapDtoToDiscount(discount);
        return mapper.mapToDiscountDto(discountDAO.saveAndFlush(dis));
    }

    @Override
    public void delete(Long id) {
        discountDAO.deleteById(id);
    }
}
