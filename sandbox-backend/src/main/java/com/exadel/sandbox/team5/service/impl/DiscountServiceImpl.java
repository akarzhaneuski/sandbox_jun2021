package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.ImageDAO;
import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.dto.search.DiscountSearchCriteria;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import com.exadel.sandbox.team5.util.Pair;
import com.exadel.sandbox.team5.util.ResultPage;
import com.exadel.sandbox.team5.util.SearchCriteria;
import com.exadel.sandbox.team5.util.Sorting;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class DiscountServiceImpl extends CRUDServiceDtoImpl<DiscountDAO, Discount, DiscountDto> implements DiscountService {

    private final ReviewDAO reviewDAO;
    private final ImageDAO imageDAO;

    public DiscountServiceImpl(DiscountDAO entityDao, MapperConverter mapper, ReviewDAO reviewDAO, ImageDAO imageDAO) {
        super(entityDao, Discount.class, DiscountDto.class, mapper);
        this.reviewDAO = reviewDAO;
        this.imageDAO = imageDAO;
    }

    @Override
    public ResultPage<DiscountDto> getAllByCriteria(SearchCriteria criteria) {
        Page<Discount> discounts = entityDao.findAll(criteria.getPageRequest());
        return mapDto(discounts);
    }

    @Override
    public DiscountDto getById(Long id) {
        Discount discount = entityDao.findById(id).orElseThrow(NoSuchElementException::new);
        DiscountDto discountDto = mapper.map(discount, DiscountDto.class);
        discountDto.setRate(reviewDAO.findRate(discountDto.getId()));
        if (discount.getImageId() != null) {
            discountDto.setNameImage(imageDAO.getById(discount.getImageId()).getName());
        }
        return discountDto;
    }

    @Override
    public List<DiscountDto> getAll() {
        return getAllByCriteria(new SearchCriteria()).getContent();
    }

    @Override
    public DiscountDto save(DiscountDto discount) {
        Discount dis = mapper.map(discount, Discount.class);
        if (discount.getNameImage() != null) {
            dis.setImageId(imageDAO.findImageByName(discount.getNameImage()).orElseThrow(NoSuchElementException::new).getId());
        }
        return mapper.map(entityDao.saveAndFlush(dis), DiscountDto.class);
    }

    @Override
    public DiscountDto update(DiscountDto discount) {
        return this.save(discount);
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


    //fixme добавить стандартную сортировку по дате добавления после включения аудита!
    @Override
    public ResultPage<DiscountDto> getByCriteria(DiscountSearchCriteria searchCriteria) {
        if (searchCriteria.isEmpty()) {
            return getAllByCriteria(searchCriteria);
        }
        if (searchCriteria.getOrders() != null &&
                searchCriteria.getOrders().contains(new Sorting("ASC", "rate"))) {
            searchCriteria.setOrders(Collections.emptyList());
            return findDiscountsByCriteriaSortedByRate(searchCriteria);
        }
        Page<Discount> discounts = findDiscountsByCriteria(searchCriteria);
        return mapDto(discounts);
    }

    private ResultPage<DiscountDto> findDiscountsByCriteriaSortedByRate(DiscountSearchCriteria searchCriteria) {
        int pageNum = searchCriteria.getPageNum();
        int itemOnPage = searchCriteria.getItemsPerPage();
        DiscountSearchCriteria criteria = new DiscountSearchCriteria(0, Integer.MAX_VALUE,
                searchCriteria.getOrders(), searchCriteria.getTags(), searchCriteria.getRate(),
                searchCriteria.getSearchText(), searchCriteria.getLocationCriteria(),
                searchCriteria.getCompanies(), searchCriteria.getCategory());
        Page<Discount> discounts = findDiscountsByCriteria(criteria);
        ResultPage<DiscountDto> discountsDto = mapDto(discounts);
        List<DiscountDto> sorted = new ArrayList<>(discountsDto.getContent());
        sorted.sort((o1, o2) -> (int) (o2.getRate() - o1.getRate()));
        List<DiscountDto> result = sorted.stream()
                .skip((long) pageNum * itemOnPage)
                .limit(itemOnPage)
                .collect(Collectors.toList());
        return new ResultPage<>(result, discountsDto.getTotalElements());
    }

    private Page<Discount> findDiscountsByCriteria(DiscountSearchCriteria searchCriteria) {
        return entityDao.findDiscountsByCriteria(searchCriteria.getSearchText(),
                searchCriteria.getTags(),
                searchCriteria.getLocationCriteria().getCountry(),
                searchCriteria.getLocationCriteria().getCities(),
                searchCriteria.getCompanies(),
                searchCriteria.getCategory(),
                searchCriteria.getRate(),
                searchCriteria.getPageRequest());
    }

    private ResultPage<DiscountDto> mapDto(Page<Discount> discounts) {
        ResultPage<DiscountDto> result = mapper.mapToPage(discounts, DiscountDto.class);
        setRate(getRate(result.getContent()), result.getContent());
        setNameImage(discounts, result);
        return result;
    }

    private Map<Long, Double> getRate(List<DiscountDto> result) {
        Set<Long> discountIds = result.stream().map(DiscountDto::getId).collect(Collectors.toSet());
        return reviewDAO.getRateByDiscountId(discountIds).stream()
                .collect(Collectors.toMap(x -> Long.parseLong(x.getFirst()), y -> Double.parseDouble(y.getSecond())));
    }

    private void setRate(Map<Long, Double> rateMap, List<DiscountDto> dtoList) {
        for (DiscountDto d : dtoList) {
            if (rateMap.get(d.getId()) == null) d.setRate(0.0);
            else d.setRate(rateMap.get(d.getId()));
        }
    }

    private void setNameImage(Page<Discount> discounts, ResultPage<DiscountDto> result) {
        List<Long> imageId = discounts.getContent().stream().map(Discount::getImageId).collect(Collectors.toList());
        Map<Long, String> namesImages = imageDAO.getAllName(imageId).stream()
                .collect(Collectors.toMap(x -> Long.parseLong(x.getFirst()), Pair::getSecond));
        Map<Long, String> nameImageToDto = new HashMap<>();
        discounts.forEach(d -> nameImageToDto.put(d.getId(), namesImages.get(d.getImageId())));
        result.getContent().forEach(discountDto -> discountDto.setNameImage(nameImageToDto.get(discountDto.getId())));
    }
}
