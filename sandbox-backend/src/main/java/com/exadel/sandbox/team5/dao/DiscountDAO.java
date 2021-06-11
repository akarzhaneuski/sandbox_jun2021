package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountDAO extends JpaRepository<Discount, Long> {

    Page<Discount> findAll(Pageable pageable);

    Page<Discount> getAllByTagsId(Long id, Pageable pageable);
}
