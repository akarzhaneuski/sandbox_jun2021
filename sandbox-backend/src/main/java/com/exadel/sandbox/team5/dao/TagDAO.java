package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDAO extends CommonRepository<Tag> {

    @Query(value = """
            SELECT c.id
            FROM sandbox.tag c
            WHERE c.tagName=(:tagName);
            """, nativeQuery = true)
    Long findTagIdByName(String tagName);
}
