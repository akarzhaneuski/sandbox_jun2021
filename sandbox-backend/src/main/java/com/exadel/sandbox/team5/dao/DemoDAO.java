package com.exadel.sandbox.team5.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exadel.sandbox.team5.entity.DemoEntity;

@Repository
public interface DemoDAO extends JpaRepository<DemoEntity, Long> {
}
