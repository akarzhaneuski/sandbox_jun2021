package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Orders, Long> {

    List<Orders> getOrderByEmployeeId(Long id);
}
