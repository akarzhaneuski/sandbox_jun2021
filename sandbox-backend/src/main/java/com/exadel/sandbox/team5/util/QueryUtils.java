package com.exadel.sandbox.team5.util;

import com.exadel.sandbox.team5.dto.DiscountDto;

import java.util.List;
import java.util.Map;

public class QueryUtils {
    private QueryUtils() {
    }

    public static String getWildcard(String text) {
        return "%" + text + "%";
    }

    public static List<DiscountDto> setRate(Map<Long, Double> rtMap, List<DiscountDto> dtoList) {
        for (DiscountDto d : dtoList) {
            d.setRate(rtMap.get(d.getId()));
        }
        return dtoList;
    }
}
