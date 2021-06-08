package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDAO extends JpaRepository<Location, Long> {
}
