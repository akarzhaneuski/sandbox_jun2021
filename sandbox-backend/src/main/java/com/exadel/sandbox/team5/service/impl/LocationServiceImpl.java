package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.LocationDAO;
import com.exadel.sandbox.team5.entity.LocationEntity;
import com.exadel.sandbox.team5.service.LocationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationDAO dao;

    @Override
    public LocationEntity getById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<LocationEntity> getAll() {
        return dao.findAll();
    }

    @Override
    public LocationEntity save(LocationEntity locationEntity) {
        return dao.save(locationEntity);
    }

    @Override
    public LocationEntity update(LocationEntity locationEntity) {
        return dao.save(locationEntity);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
