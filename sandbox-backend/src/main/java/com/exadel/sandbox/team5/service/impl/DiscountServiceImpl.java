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
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResultPage<DiscountDto> getAllSort(SearchCriteria criteria) {
        Page<Discount> dis = discountDAO.findAll(criteria.getPageRequest());
        ResultPage<DiscountDto> discountDTOs = mapper.mapToPage(dis, DiscountDto.class);
        setRate(getRate(discountDTOs.getContent()), discountDTOs.getContent());
        return discountDTOs;
    }

    @Override
    public DiscountDto getById(Long id) {
        DiscountDto discountDto = discountDAO.findById(id)
                .map(discount -> mapper.map(discount, DiscountDto.class))
                .orElseThrow(NoSuchElementException::new);
        discountDto.setRate(reviewDAO.findRate(discountDto.getId()));
        return discountDto;
    }

    //fixme What should this method do?
    @Override
    public List<DiscountDto> getAll() {
        List<Discount> discounts = discountDAO.findAll();
        return mapper.mapAll(discounts, DiscountDto.class);
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
    public ResultPage<DiscountDto> getByCriteria(DiscountSearchCriteria searchCriteria) {
        if (searchCriteria.isEmpty()) {
            return getAllSort(searchCriteria);
        }
        String searchText = StringUtils.isNullOrEmpty(searchCriteria.getSearchText())
                ? null
                : QueryUtils.getWildcard(searchCriteria.getSearchText());

        var result = discountDAO.findDiscountsByCriteria(searchText,
                searchCriteria.getTags(), searchCriteria.getLocationCriteria().getCountry(),
                searchCriteria.getLocationCriteria().getCities(), searchCriteria.getRate(),
                searchCriteria.getPageRequest());

        if (searchCriteria.getOrders().isEmpty() || searchCriteria.getOrders().get(0).getSortBy().equals("rate")) {
        }
        ResultPage<DiscountDto> discountDTOs = mapper.mapToPage(result, DiscountDto.class);
        setRate(getRate(discountDTOs.getContent()), discountDTOs.getContent());
        return discountDTOs;
    }

    private Map<Long, Double> getRate(List<DiscountDto> result) {
        Set<Long> discountIds = result.stream().map(DiscountDto::getId).collect(Collectors.toSet());
        return reviewDAO.getRateByDiscountId(discountIds).stream()
                .collect(Collectors.toMap(x -> Long.parseLong(x.getFirst()), y -> Double.parseDouble(y.getSecond())));
    }

    //реализовать сортировку по рейтингу
    private List<DiscountDto> setRate(Map<Long, Double> rtMap, List<DiscountDto> dtoList) {
        for (DiscountDto d : dtoList) {
            if (rtMap.get(d.getId()) == null) d.setRate(0.0);
            else d.setRate(rtMap.get(d.getId()));
        }
        return dtoList;
    }
}
