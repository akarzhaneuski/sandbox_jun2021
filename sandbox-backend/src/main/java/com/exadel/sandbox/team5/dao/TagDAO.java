package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Tag;
import com.exadel.sandbox.team5.util.PairSL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDAO extends JpaRepository<Tag, Long> {

    @Query(value = """
            SELECT new com.exadel.sandbox.team5.util.PairSL(t.name,COUNT(o.id))
            FROM Tag t 
                LEFT JOIN t.discounts d
                LEFT JOIN d.tags dt
                LEFT JOIN Order o ON d.id=o.discount.id
            WHERE t.id=(dt.id) AND d.id=dt.id AND d.id=o.discount.id
                GROUP BY t.name
            """)
    List<PairSL> getAllOrders();
}
