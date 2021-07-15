package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ImageDAO extends CommonRepository<Image> {
    Optional<Image> findImageByName(String name);

    @Query(value = """
                            SELECT img.id, img.name FROM images img WHERE img.id IN (:imageId)
            """, nativeQuery = true)
    Map<Long, String> getAllName(@Param("imageId") List<Long> imageId);
}
