package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDAO extends JpaRepository<Country, Long> {
}
