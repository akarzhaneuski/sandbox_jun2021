package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.LocationDAO;
import com.exadel.sandbox.team5.dto.CountryDto;
import com.exadel.sandbox.team5.entity.Country;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationDAO dao;
    private final MapperConverter mapper;

    @Override
    public CountryDto getById(Long id) {
        return dao.findById(id)
                .map(country -> mapper.map(country, CountryDto.class))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<CountryDto> getAll() {
        return mapper.mapAll(dao.findAll(), CountryDto.class);
    }

    @Override
    public CountryDto save(CountryDto countryDto) {
        var country = mapper.map(countryDto, Country.class);
        return mapper.map(dao.save(country), CountryDto.class);
    }

    @Override
    public CountryDto update(CountryDto countryDto) {
        return this.save(countryDto);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
