package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.LocationDAO;
import com.exadel.sandbox.team5.dto.LocationDto;
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
    public LocationDto getById(Long id) {
        return dao.findById(id)
                .map(country -> mapper.map(country, LocationDto.class))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<LocationDto> getAll() {
        return mapper.mapAll(dao.findAll(), LocationDto.class);
    }

    @Override
    public LocationDto save(LocationDto location) {
        Country country = mapper.map(location, Country.class);
        return mapper.map(dao.save(country), LocationDto.class);
    }

    @Override
    public LocationDto update(LocationDto location) {
        return this.save(location);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
