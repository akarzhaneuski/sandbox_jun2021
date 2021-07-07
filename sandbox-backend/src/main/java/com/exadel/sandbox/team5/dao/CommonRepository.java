package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<E extends BaseEntity> extends JpaRepository<E, Long> {
}
