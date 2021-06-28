package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.util.DiscountSearchCriteria;
import com.exadel.sandbox.team5.util.Pair;
import com.exadel.sandbox.team5.util.QueryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class DiscountServiceImpl implements DiscountService {

    private final DiscountDAO discountDAO;
    private final MapperConverter mapper;
    private final ReviewDAO reviewDAO;

    @Override
    public DiscountDto getById(Long id) {
        var discountDto = discountDAO.findById(id)
                .map(discount -> mapper.map(discount, DiscountDto.class))
                .orElseThrow(NoSuchElementException::new);
        setAverageRate(discountDto);
        return discountDto;
    }

    private void setAverageRate(DiscountDto discountDto) {
        discountDto.setRate(reviewDAO.findRate(discountDto.getId()));
    }

    @Override
    public List<DiscountDto> getAll() {
        List<DiscountDto> resultWithoutRage = mapper.mapAll(discountDAO.findAll(), DiscountDto.class);
        resultWithoutRage.forEach(this::setAverageRate);
        return resultWithoutRage;
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
        String searchText = QueryUtils.getWildcard(searchCriteria.getSearchText());
        List<Discount> result;
        if (searchCriteria.getTags() == null || searchCriteria.getTags().isEmpty()) {
            result = discountDAO.getByCriteria(searchText, searchCriteria.getRate());
        } else {
            result = discountDAO.getByCriteriaWithTags(searchText,
                    searchCriteria.getTags(), searchCriteria.getRate());
        }
        Set<Long> discountIds = result.stream().map(Discount::getId).collect(Collectors.toSet());
        Map<Long, Double> rateList = reviewDAO.getRateByDiscountId(discountIds).stream()
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
        List<DiscountDto> discountDTOs = mapper.mapAll(result, DiscountDto.class);
        discountDTOs = setRate(rateList, discountDTOs);
        return new PageImpl<>(discountDTOs, searchCriteria.getPageRequest(), discountDTOs.size());
    }

    public static List<DiscountDto> setRate(Map<Long, Double> rtMap, List<DiscountDto> dtoList) {
        for (DiscountDto d : dtoList) {
            if (rtMap.get(d.getId()) == null) d.setRate(0.0);
            else d.setRate(rtMap.get(d.getId()));
        }
        return dtoList;
    }
}
