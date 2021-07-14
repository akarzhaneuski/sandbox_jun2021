package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDAO extends CommonRepository<Image> {
    Optional<Image> findImageByName(String name);

}
