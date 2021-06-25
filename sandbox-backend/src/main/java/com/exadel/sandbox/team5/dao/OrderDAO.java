package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Long> {

    List<Order> findAllByEmployeeId(Long id);

    List<Order> findAllByDiscountId(Long discountId);

    Order getOrderByDiscountIdAndEmployeePromocode(Long id, String promoCode);

    @Modifying
    @Query(value = "update `order` o set o.promoCodeStatus = :status where o.employeePromocode = :promoCode", nativeQuery = true)
    void setPromoCodeStatus(@Param("status") Boolean status, @Param("promoCode") String promoCode);

    @Query(value = """
            SELECT o.* FROM `order` o
                LEFT JOIN discount d ON o.discountId = d.id
                LEFT JOIN company c ON d.companyId = c.id
            WHERE c.id=(:companyId)
                GROUP BY o.id;
                """, nativeQuery = true)
    List<Order> getOrdersByCompanyIds(@Param("companyId") Long companyId);

    @Query(value = """
            SELECT o.* FROM `order` o
                LEFT JOIN discount d ON o.discountId = d.id
                LEFT JOIN discount_tag dt on d.id = dt.discountId
                LEFT JOIN tag t on t.id = dt.tagId
            WHERE t.tagName IN (:tags)
                GROUP BY o.id;
            """, nativeQuery = true)
    List<Order> getOrdersByTags(@Param("tags") List<String> tags);
}
