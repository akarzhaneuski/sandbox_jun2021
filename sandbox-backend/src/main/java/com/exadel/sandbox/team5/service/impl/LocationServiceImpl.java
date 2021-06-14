package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.LocationDAO;
import com.exadel.sandbox.team5.entity.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl extends CRUDServiceImpl<Location, LocationDAO> {

    public LocationServiceImpl(LocationDAO repository) {
        super(repository);
    }
}
