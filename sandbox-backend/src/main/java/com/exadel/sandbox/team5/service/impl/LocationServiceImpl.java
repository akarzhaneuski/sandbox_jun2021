package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.LocationDAO;
import com.exadel.sandbox.team5.entity.Location;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LocationServiceImpl extends AbstractService<Location, LocationDAO> {

    public LocationServiceImpl(LocationDAO repository) {
        super(repository);
    }
}
