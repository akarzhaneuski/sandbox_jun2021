package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dao.TagDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Tag;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.util.DiscountSearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountDAO discountDAO;
    private final MapperConverter mapper;
    private final TagDAO tagDAO;
    private final ReviewDAO reviewDAO;

    @Override
    public DiscountDto getById(Long id) {
        DiscountDto discountId = discountDAO.findById(id)
                .map(discount -> mapper.map(discount, DiscountDto.class))
                .orElseThrow(NoSuchElementException::new);
        discountId = setAvarageRate(discountId);
        return discountId;
    }

    private DiscountDto setAvarageRate(DiscountDto discountId) {
        if (discountId == null) {
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

    @Override
    public Page<DiscountDto> getByCriteria(DiscountSearchCriteria searchCriteria) {
        List<Discount> result = new ArrayList<>();
        long rate;
        PageRequest pageRequest = searchCriteria.getPageRequest();
        Set<Tag> tags = tagDAO.getTagsByNameIn(searchCriteria.getTags());
        String searchText = searchCriteria.getSearchText();
        String sortBy = searchCriteria.getSortBy();
        List<Discount> discounts = discountDAO.getByNameContaining(searchText);
        discounts = discounts.stream().filter(x -> x.getTags().containsAll(tags)).collect(Collectors.toList());
        if (sortBy.equals("Top rated")) {
            rate = 5;
            List<Long> discountsId = reviewDAO.getDiscountsIdByRate(rate);
            for (Discount d : discounts) {
                for (long id : discountsId) {
                    if (d.getId() == id) {
                        result.add(d);
                        break;
                    }
                }
            }
        }
        List<DiscountDto> discountDtos = mapper.mapAll(result, DiscountDto.class);
        return new PageImpl<>(discountDtos, pageRequest, discountDtos.size());
    }
}
