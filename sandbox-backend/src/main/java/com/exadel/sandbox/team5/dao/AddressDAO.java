package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressDAO extends CommonRepository<Address>, AddressCustomDAO {

    @Query(value = """
                    SELECT adr.* FROM company c
                        LEFT JOIN company_address ca ON c.id = ca.companyId
                        LEFT JOIN address adr ON ca.addressId = adr.id
                        Left JOIN city cit ON adr.cityId = cit.id
                        LEFT JOIN country cou ON cit.countryId = cou.id
                        WHERE c.id = :companyId
            """, nativeQuery = true)
    List<Address> findByCompanyId(@Param("companyId") Long id);

    @Query(value = "SELECT id FROM City WHERE name LIKE :cityName")
    Optional<Long> findIdCityByName(@Param("cityName") String cityName);

    @Query(value = "SELECT id FROM Country WHERE name LIKE :countryName")
    Optional<Long> findIdCountryByName(@Param("countryName") String countryName);
}
