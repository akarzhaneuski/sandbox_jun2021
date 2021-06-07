package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.LocationEntity;

import java.util.List;

public interface LocationService {

    LocationEntity getById(Long id);

    List<LocationEntity> getAll();

    LocationEntity save(LocationEntity locationEntity);

    LocationEntity update(LocationEntity locationEntity);

    void delete(Long id);
}
