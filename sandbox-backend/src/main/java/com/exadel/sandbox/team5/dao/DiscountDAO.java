package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.util.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DiscountDAO extends CommonRepository<Discount> {

    @Query(value = """
            SELECT d.*, AVG(COALESCE(r.rate, 0)) rate
                        FROM discount d
                            LEFT JOIN discount_tag dt ON d.id = dt.discountId
                            LEFT JOIN tag t ON t.id = dt.tagId
                            LEFT JOIN country c ON d.countryId = c.id 
                            LEFT JOIN discount_address da ON d.id = da.discountId
                            LEFT JOIN address a ON da.addressId = a.id
                            LEFT JOIN city s ON a.cityId = s.id  
                            LEFT JOIN company co ON d.companyId = co.id 
                            LEFT JOIN category ct ON d.categoryId = ct.id             
                            LEFT JOIN review r ON d.id = r.discountId
            WHERE
             case when regexp_like(:name, '[a-zA-Z0-9_]')
             then (
                        :name is null or d.description like :name or d.name like :name 
                        or soundex_match(:name, d.name, ' ')
                        or soundex_match(:name, d.description, ' ')
                        or soundex_match_all(:name, d.name, ' ')
                        or soundex_match_all(:name, d.description, ' ')
                   ) else (
                        :name is null or d.description like :name or d.name like :name
                   )
                   end 
                            AND (coalesce(:tags, null) is null or t.tagName in (:tags))
                            AND (:country is null or c.name = :country)
                            AND (coalesce(:cities, null) is null or s.name in (:cities))
                            AND (coalesce(:companies, null) is null or co.name in (:companies))
                            AND (coalesce(:categories, null) is null or ct.name in (:categories))
            GROUP BY d.id
                HAVING rate>=(:rate)
            """,
            countQuery = """
                            SELECT COUNT(distinct d.id), AVG(COALESCE(r.rate, 0)) rate
                                        FROM discount d
                                            LEFT JOIN discount_tag dt ON d.id = dt.discountId
                                            LEFT JOIN tag t ON t.id = dt.tagId
                                            LEFT JOIN country c ON d.countryId = c.id 
                                            LEFT JOIN discount_address da ON d.id = da.discountId
                                            LEFT JOIN address a ON da.addressId = a.id
                                            LEFT JOIN city s ON a.cityId = s.id  
                                            LEFT JOIN company co ON d.companyId = co.id    
                                            LEFT JOIN category ct ON d.categoryId = ct.id            
                                            LEFT JOIN review r ON d.id = r.discountId
                    WHERE 
                     case when regexp_like(:name, '[a-zA-Z0-9_]')
                     then (
                                :name is null or d.description like :name or d.name like :name 
                                or soundex_match(:name, d.name, ' ')
                                or soundex_match(:name, d.description, ' ')
                                or soundex_match_all(:name, d.name, ' ')
                                or soundex_match_all(:name, d.description, ' ')
                           ) else (
                                :name is null or d.description like :name or d.name like :name
                           )
                           end 
                                    AND (coalesce(:tags, null) is null or t.tagName in (:tags))
                                    AND (:country is null or c.name = :country)
                                    AND (coalesce(:cities, null) is null or s.name in (:cities))
                                    AND (coalesce(:companies, null) is null or co.name in (:companies))
                                    AND (coalesce(:categories, null) is null or ct.name in (:categories))
                                                            """, nativeQuery = true)
    Page<Discount> findDiscountsByCriteria(@Param("name") String searchText,
                                           @Param("tags") Set<String> tags,
                                           @Param("country") String country,
                                           @Param("cities") Set<String> cities,
                                           @Param("companies") Set<String> companies,
                                           @Param("categories") Set<String> categories,
                                           @Param("rate") double rate,
                                           Pageable pageable);


    @Query(value = """
            SELECT DISTINCT new com.exadel.sandbox.team5.util.Pair(d.name, COUNT(o.id))
            FROM Discount d
                LEFT JOIN Order o ON d.id=o.discount.id
            WHERE d.id=o.discount.id 
                GROUP BY d.id
            """)
    List<Pair> getAllOrdersForDiscounts();

    @Modifying
    @Query(value = """
            UPDATE discount SET views = views + 1 WHERE discount.id = (:discountId);
            """, nativeQuery = true)
    void incrementViewsByDiscountId(@Param("discountId") Long discountId);

    @Query(value = """
            SELECT new com.exadel.sandbox.team5.util.Pair(d.name, d.views) FROM Discount d WHERE d.views > 0 order by d.name
            """)
    List<Pair> getViewsByDiscounts();

    @Modifying
    @Query(value = """
            UPDATE Discount d SET d.isSent=1 WHERE d.id IN (:discountIds)
            """)
    void markDiscountsAsSent(List<Long> discountIds);

    Set<Discount> getDiscountsByIdIsIn(Set<Long> discountIds);

    @Query(value = """
            SELECT d.name
            FROM discount d
            LEFT JOIN `order` o on d.id = o.discountId
            WHERE o.employeePromocode=(:uuid);
            """, nativeQuery = true)
    String getDiscountNameByOrderUUID(@Param("uuid") String uuid);
}
