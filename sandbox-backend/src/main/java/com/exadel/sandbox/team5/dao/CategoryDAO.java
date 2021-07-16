package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends CommonRepository<Category>{

    @Query (value = """
            SELECT c.id
            FROM category c
            WHERE c.name=(:name);
            """, nativeQuery = true)
    Long findCategoryIdByName(String name);
}
