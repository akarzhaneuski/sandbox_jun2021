package com.exadel.sandbox.team5.util;

public class QueryUtils {
    private QueryUtils() {
    }

    public static String getWildcard(String text) {
        return "%" + text + "%";
    }
}
