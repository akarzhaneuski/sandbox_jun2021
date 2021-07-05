package com.exadel.sandbox.team5.service.impl;

import com.amazonaws.util.StringUtils;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.util.DiscountSearchCriteria;
import com.exadel.sandbox.team5.util.QueryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
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
        DiscountDto discountDto = discountDAO.findById(id)
                .map(discount -> mapper.map(discount, DiscountDto.class))
                .orElseThrow(NoSuchElementException::new);
        discountDto.setRate(reviewDAO.findRate(discountDto.getId()));
        return discountDto;
    }

    @Override
    public List<DiscountDto> getAll() {
        List<Discount> discounts = discountDAO.findAll();
        return setRate(getRate(discounts), mapper.mapAll(discounts, DiscountDto.class));
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
        String searchText = StringUtils.isNullOrEmpty(searchCriteria.getSearchText())
                ? null
                : QueryUtils.getWildcard(searchCriteria.getSearchText());

        var result = discountDAO.findDiscountsByCriteria(searchText,
                searchCriteria.getTags(), searchCriteria.getLocationCriteria().getCountry(),
                searchCriteria.getLocationCriteria().getCities(), searchCriteria.getRate());
        List<DiscountDto> discountDTOs = mapper.mapAll(result, DiscountDto.class);
        setRate(getRate(result), discountDTOs);
        return new PageImpl<>(discountDTOs, searchCriteria.getPageRequest(), discountDTOs.size());
    }

    private Map<Long, Double> getRate(List<Discount> result) {
        Set<Long> discountIds = result.stream().map(Discount::getId).collect(Collectors.toSet());
        return reviewDAO.getRateByDiscountId(discountIds).stream()
                .collect(Collectors.toMap(x -> Long.parseLong(x.getFirst()), y -> Double.parseDouble(y.getSecond())));
    }

    public static List<DiscountDto> setRate(Map<Long, Double> rtMap, List<DiscountDto> dtoList) {
        for (DiscountDto d : dtoList) {
            if (rtMap.get(d.getId()) == null) d.setRate(0.0);
            else d.setRate(rtMap.get(d.getId()));
        }
        return dtoList;
    }
}
