package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Order;
import com.exadel.sandbox.team5.util.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderDAO extends CommonRepository<Order> {

    Order getOrderByEmployeePromocode(String uuid);

    @Modifying
    @Query(value = "update `order` o set o.promoCodeStatus = :status where o.employeePromocode = :promoCode", nativeQuery = true)
    void setPromoCodeStatus(@Param("status") Boolean status, @Param("promoCode") String promoCode);

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

    @Query(value = """
                        SELECT d FROM Order o
                        LEFT JOIN Discount d ON o.discount.id = d.id
                        WHERE o.employee.id = :id
            """)
    Page<Discount> findOrderByEmployeeId(@Param("id") Long id, Pageable pageable);
}
