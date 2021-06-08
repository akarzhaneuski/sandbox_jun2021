package com.exadel.sandbox.team5.dao;

import com.exadel.sandbox.team5.entity.DiscountEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<DiscountEntity, Integer> {

}
