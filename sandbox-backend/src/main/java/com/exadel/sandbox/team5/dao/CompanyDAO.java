package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDAO extends CommonRepository<Company> {

    List<Company> findAllByLocationsId(long id);
}
