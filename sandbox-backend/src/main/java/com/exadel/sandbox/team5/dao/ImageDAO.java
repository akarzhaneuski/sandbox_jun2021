package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Image;
import com.exadel.sandbox.team5.util.Pair;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageDAO extends CommonRepository<Image> {
    Optional<Image> findImageByName(String name);

    @Query(value = """
            SELECT  new com.exadel.sandbox.team5.util.Pair(img.id, img.name) FROM Image img WHERE img.id IN (:imageId)
            """)
    List<Pair> getAllName(@Param("imageId") List<Long> imageId);

}
