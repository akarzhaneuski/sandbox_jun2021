package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.util.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DiscountDAO extends JpaRepository<Discount, Long> {

    @Query(value = """
            SELECT d.*, AVG(r.rate) rate
                        FROM discount d
                            LEFT JOIN discount_tag dt ON d.id = dt.discountId
                            LEFT JOIN tag t ON t.id = dt.tagId
                            LEFT JOIN country c ON d.countryId = c.id 
                            LEFT JOIN discount_address da ON d.id = da.discountId
                            LEFT JOIN address a ON da.addressId = a.id
                            LEFT JOIN city s ON a.cityId = s.id                    
                            LEFT JOIN review r ON d.id = r.discountId
            WHERE (:name is null or d.description like :name or d.name like :name)
                            AND (:tags is null or t.tagName = :tags)
                            AND (:country is null or c.name = :country)
                            AND (:cities is null or s.name = :cities)
            GROUP BY d.id
                HAVING rate>=(:rate);
            """, nativeQuery = true)
    List<Discount> findDiscountsByCriteria(@Param("name") String searchText,
                                           @Param("tags") Set<String> tags,
                                           @Param("country") String country,
                                           @Param("cities") Set<String> cities,
                                           @Param("rate") int rate);

    @Query(value = """
            SELECT new com.exadel.sandbox.team5.util.Pair(d.name, COUNT(o.id))
            FROM Discount d
                LEFT JOIN Order o ON d.id=o.discount.id
            WHERE d.id=o.discount.id
                GROUP BY d.id
            """)
    List<Pair> getAllOrdersForDiscounts();
}
