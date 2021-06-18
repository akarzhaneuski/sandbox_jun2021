package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountDAO discountDAO;
    private final MapperConverter mapper;
    private final ReviewDAO reviewDAO;

    @Override
    public DiscountDto getById(Long id) {
        DiscountDto discountId = discountDAO.findById(id)
                .map(discount -> mapper.map(discount, DiscountDto.class))
                .orElse(null);
        discountId = setAvarageRate(discountId);
        return discountId;
    }

    private DiscountDto setAvarageRate(DiscountDto discountId) {
        if(discountId == null){
            return null;
        }
        discountId.setRate(reviewDAO.findRate(discountId.getId()));
        return discountId;
    }

    @Override
    public List<DiscountDto> getAll() {
        List<DiscountDto> resultWithoutRage = mapper.mapAll(discountDAO.findAll(), DiscountDto.class);
        return
        resultWithoutRage.stream().map(this::setAvarageRate).collect(Collectors.toList());
    }

    @Override
    public DiscountDto save(DiscountDto discount) {
        Discount dis = mapper.map(discount, Discount.class);
        return mapper.map(discountDAO.saveAndFlush(dis), DiscountDto.class);
    }

    @Override
    public DiscountDto update(DiscountDto discount) {
        return this.save(discount);
    }

    @Override
    public void delete(Long id) {
        discountDAO.deleteById(id);
    }
}
