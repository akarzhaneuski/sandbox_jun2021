package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Location;

import com.exadel.sandbox.team5.service.impl.LocationServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationRestControllerImpl extends BaseControllerImpl<Location, LocationServiceImpl> {

    public LocationRestControllerImpl(LocationServiceImpl service) {
        super(service);
    }
}
