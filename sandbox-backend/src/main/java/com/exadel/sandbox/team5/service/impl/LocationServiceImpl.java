package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.LocationDAO;
import com.exadel.sandbox.team5.entity.Location;
import com.exadel.sandbox.team5.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationDAO dao;

    @Override
    public Location getById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Location> getAll() {
        return dao.findAll();
    }

    @Override
    public Location save(Location location) {
        return dao.save(location);
    }

    @Override
    public Location update(Location location) {
        return dao.save(location);
    }

    @Override
    public void delete(Long id) {
        dao.deleteById(id);
    }
}
