package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Order;
import com.exadel.sandbox.team5.util.Pair;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderDAO extends CommonRepository<Order> {

    List<Order> findAllByEmployeeId(Long id);

    Order getOrderByDiscountIdAndEmployeePromocode(Long id, String promoCode);

    @Modifying
    @Query(value = "update `order` o set o.promoCodeStatus = :status where o.employeePromocode = :promoCode", nativeQuery = true)
    void setPromoCodeStatus(@Param("status") Boolean status, @Param("promoCode") String promoCode);

    @Query(value = """
            SELECT COUNT(o.id) FROM `order` o
                LEFT JOIN discount d ON o.discountId = d.id
                LEFT JOIN company c ON d.companyId = c.id
            WHERE c.id=(:companyId)
                GROUP BY o.id;
                """, nativeQuery = true)
    int getOrdersByCompanyId(@Param("companyId") Long companyId);

    @Query(value = """
            SELECT COUNT(o.id) FROM `order` o
                LEFT JOIN discount d ON o.discountId = d.id
            WHERE d.id=(:discountId)
                GROUP BY o.id;
                """, nativeQuery = true)
    int getOrdersByDiscountId(@Param("discountId") Long discountId);

    @Query(value = """
            SELECT COUNT(o.id) FROM `order` o
                LEFT JOIN discount d ON o.discountId = d.id
                LEFT JOIN discount_tag dt on d.id = dt.discountId
                LEFT JOIN tag t on t.id = dt.tagId
            WHERE t.tagName=(:tag)
                GROUP BY o.id;
            """, nativeQuery = true)
    int getOrdersByTag(@Param("tag") String tag);

    @Query(value = """
            SELECT new com.exadel.sandbox.team5.util.Pair(t.name, COUNT(o.id))
            FROM Order o
                JOIN o.discount d
                JOIN d.tags t
            GROUP BY t.id
            """)
    List<Pair> getAllOrdersForTags();

    @Query(value = """
            SELECT new com.exadel.sandbox.team5.util.Pair(c.name, COUNT(o.id))
            FROM Order o
                JOIN o.discount d
                LEFT JOIN d.tags t
                JOIN t.category c
            GROUP BY c.id
            """)
    List<Pair> getAllOrdersForCategories();

    @Modifying
    @Query(value = """
            update `order` o set o.promoCodeStatus = 0 where o.promoCodePeriodEnd < (:currentTime)
            """, nativeQuery = true)
    void changePromoCodeStatusAfterExpirationTime(@Param("currentTime") Date currentTime);
}
