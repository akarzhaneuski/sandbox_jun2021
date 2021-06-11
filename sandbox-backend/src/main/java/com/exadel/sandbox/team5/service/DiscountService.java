package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountService extends CRUDService<Discount> {
    Page<Discount> getAll(Pageable pageable);

    Page<Discount> getAllByTags(Tag tag, Pageable pageable);

}
