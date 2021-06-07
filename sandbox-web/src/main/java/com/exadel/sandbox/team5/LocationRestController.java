package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.LocationEntity;
import com.exadel.sandbox.team5.service.LocationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationRestController {

    private final LocationService service;

    @GetMapping("/{id}")
    public LocationEntity getLocation(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<LocationEntity> getAll(){
        return service.getAll();
    }

    @PostMapping
    public LocationEntity save(@RequestBody LocationEntity entity){
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public LocationEntity update(@PathVariable Long id, @RequestBody LocationEntity entity){
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
