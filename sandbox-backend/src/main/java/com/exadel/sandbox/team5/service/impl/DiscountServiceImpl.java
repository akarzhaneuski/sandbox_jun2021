package com.exadel.sandbox.team5.service.impl;

import com.amazonaws.util.StringUtils;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.search.DiscountSearchCriteria;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;

import com.exadel.sandbox.team5.util.Pair;

import com.exadel.sandbox.team5.util.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class DiscountServiceImpl extends CRUDServiceDtoImpl<DiscountDAO, Discount, DiscountDto> implements DiscountService {

    private final ReviewDAO reviewDAO;

    public DiscountServiceImpl(DiscountDAO entityDao, MapperConverter mapper, ReviewDAO reviewDAO) {
        super(entityDao, Discount.class, DiscountDto.class, mapper);
        this.reviewDAO = reviewDAO;
    }

    @Override
    public DiscountDto getById(Long id) {
        DiscountDto discountDto = entityDao.findById(id)
                .map(discount -> mapper.map(discount, DiscountDto.class))
                .orElseThrow(NoSuchElementException::new);
        discountDto.setRate(reviewDAO.findRate(discountDto.getId()));
        return discountDto;
    }

    @Override
    public Page<DiscountDto> getByCriteria(DiscountSearchCriteria searchCriteria) {
        String searchText = StringUtils.isNullOrEmpty(searchCriteria.getSearchText())
                ? null
                : QueryUtils.getWildcard(searchCriteria.getSearchText());
        Set<String> tags = QueryUtils.safeCollectionParam(searchCriteria.getTags());
        Set<String> cities = QueryUtils.safeCollectionParam(searchCriteria.getLocationCriteria().getCities());
        Set<String> companies = QueryUtils.safeCollectionParam(searchCriteria.getCompanies());

        var result = entityDao.findDiscountsByCriteria(searchText,
                tags, searchCriteria.getLocationCriteria().getCountry(),
                cities, companies, searchCriteria.getRate());
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

    @Override
    public Map<String, String> getViewsByDiscounts() {
        return entityDao.getViewsByDiscounts().stream().collect(Collectors.toMap(Pair::getFirst, Pair::getSecond, (o1, o2) -> o1, TreeMap::new));
    }

    @Override
    public void incrementViews(Long discountId) {
        entityDao.findById(discountId).orElseThrow(NoSuchElementException::new);
        entityDao.incrementViewsByDiscountId(discountId);
    }
}
