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

            SELECT o.employeePromocode
            FROM `order` o
            WHERE o.employeePromocode=(:uuid);
            """, nativeQuery = true)
    String getEmployeePromocodeByUUID(@Param("uuid") String uuid);

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
            SELECT o.promoCodeStatus
            FROM `order` o
            WHERE o.employeePromocode=(:uuid);
            """, nativeQuery = true)
    boolean getPromoCodeStatusByUUID(@Param("uuid") String uuid);

    @Query(value = """
            SELECT o.promoCodePeriodEnd
            FROM `order` o
            WHERE o.employeePromocode=(:uuid);
            """, nativeQuery = true)
    Date getPromocodePeriodEndByUUID(@Param("uuid") String uuid);
}
