package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Company;
import com.exadel.sandbox.team5.util.PairSL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDAO extends JpaRepository<Company, Long> {

    List<Company> findAllByLocationsId(long id);

    @Query(value = """
            SELECT new com.exadel.sandbox.team5.util.PairSL(c.name, COUNT(o.id))
            FROM Company c
                LEFT JOIN Discount d ON d.company.id = c.id
                LEFT JOIN Order o ON d.id=o.discount.id
            WHERE c.id=d.company.id AND d.id=o.discount.id
                GROUP BY c.name
            """)
    List<PairSL> getAllOrders();
}
