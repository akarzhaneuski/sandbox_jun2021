package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.EmployeeDiscount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDiscountDAO extends JpaRepository<EmployeeDiscount, Long> {
}
