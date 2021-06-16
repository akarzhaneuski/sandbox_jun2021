package com.exadel.sandbox.team5.mapper;

import com.exadel.sandbox.team5.dto.DiscountDTO;
import com.exadel.sandbox.team5.entity.Discount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperDTO {

    private final ModelMapper modelMapper;

    public DiscountDTO mapToDiscountDto(Discount discount) {
        return modelMapper.map(discount, DiscountDTO.class);
    }

    public Discount mapDtoToDiscount(DiscountDTO discountDTO){
        return modelMapper.map(discountDTO, Discount.class);
    }
}
