package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.util.PairSL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DiscountDAO extends JpaRepository<Discount, Long> {

    @Query(value = "SELECT d.*, AVG(r.rate) rate " +
            "FROM discount d " +
            "         LEFT JOIN discount_tag dt ON d.id = dt.discountId " +
            "         LEFT JOIN tag t ON t.id = dt.tagId " +
            "         LEFT JOIN review r ON d.id = r.discountId " +
            " WHERE " +
            "    t.tagName IN (:tags) " +
            "    AND " +
            "    d.name LIKE :name OR d.description LIKE :name " +
            "GROUP BY d.id " +
            "HAVING rate>=(:rate);", nativeQuery = true)
    List<Discount> getByCriteriaWithTags(@Param("name") String searchText,
                                         @Param("tags") Set<String> tags, @Param("rate") int rate);

    @Query(value = "SELECT d.*, AVG(r.rate) rate " +
            "FROM discount " +
            "         LEFT JOIN review r ON d.id = r.discountId " +
            " WHERE " +
            "    d.name LIKE :name OR d.description LIKE :name " +
            "GROUP BY d.id " +
            "HAVING rate>=(:rate);", nativeQuery = true)
    List<Discount> getByCriteria(@Param("name") String searchText, @Param("rate") int rate);

    @Query(value = """
            SELECT new com.exadel.sandbox.team5.util.PairSL(d.name, COUNT(o.id))
            FROM Discount d
                LEFT JOIN Order o ON d.id=o.discount.id
            WHERE d.id=o.discount.id
                GROUP BY d.name
            """)
    List<PairSL> getAllOrders();

    /*@Query(value = """
            SELECT new com.exadel.sandbox.team5.util.PairSL(t.name, COUNT(o.id))
            FROM Discount d
                LEFT JOIN Order o ON d.id=o.discount.id
                LEFT JOIN d.tags t 
            WHERE d.id=o.discount.id AND t.id=o.discount.tags
                GROUP BY t.name
            """)
    List<PairSL> getAllOrdersByTag();*/
}
