package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountService extends CRUDService<Discount> {
    Page<Discount> getAll(Pageable pageable);

    Page<Discount> getAllByTags(Long idTag, Pageable pageable);

}
