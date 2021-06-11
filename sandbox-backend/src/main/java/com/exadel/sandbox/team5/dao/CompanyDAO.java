package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyDAO extends JpaRepository<Company, Long> {

    Page<Company> findAll(Pageable pageable);
    Page<Company> findAllByLocationsId(Long id, Pageable pageable);
}
