package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.EmployeeDiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDiscountRepository extends JpaRepository<EmployeeDiscountEntity, Integer> {
}
