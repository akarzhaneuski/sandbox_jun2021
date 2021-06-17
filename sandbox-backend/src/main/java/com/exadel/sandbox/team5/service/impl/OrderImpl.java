package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.entity.Orders;
import com.exadel.sandbox.team5.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class OrderImpl implements OrderService {

    private final OrderDAO orderDAO;

    @Override
    public Orders getById(Long id) {
        return orderDAO.findById(id).orElse(null);
    }

    @Override
    public List<Orders> getAll() {
        return orderDAO.findAll();
    }

    @Override
    public Orders save(Orders orders) {
        return orderDAO.save(orders);
    }

    @Override
    public Orders update(Orders orders) {
        return orderDAO.save(orders);
    }

    @Override
    public void delete(Long id) {
        orderDAO.deleteById(id);
    }
}
