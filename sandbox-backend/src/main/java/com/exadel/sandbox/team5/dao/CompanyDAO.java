package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDAO extends JpaRepository<Company, Long> {

    @Query(value = """
            SELECT c.*
            FROM company c
                LEFT JOIN company_country cc ON cc.id=c.id
                LEFT JOIN country ct ON ct.id=cc.id
            WHERE cc.countryId=(:id)
                GROUP BY c.id;
            """, nativeQuery = true)
    List<Company> findAllByCountryId(long id);
}
