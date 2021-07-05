package com.exadel.sandbox.team5.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class QueryUtils {

    public static String getWildcard(String text) {
        return "%" + text + "%";
    }
}
