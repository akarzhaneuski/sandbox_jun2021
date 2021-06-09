package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.entity.Location;
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
    public Location getLocation(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Location> getAll(){
        return service.getAll();
    }

    @PostMapping
    public Location save(@RequestBody Location entity){
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public Location update(@PathVariable Long id, @RequestBody Location entity){
        entity.setId(id);
        return service.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
