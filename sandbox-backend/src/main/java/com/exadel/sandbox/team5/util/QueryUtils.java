package com.exadel.sandbox.team5.util;

import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class QueryUtils {

    public static String getWildcard(String text) {
        return "%" + text + "%";
    }

    public static Set<String> safeCollectionParam(Set<String> collection) {
        Set<String> emptySet = new HashSet<>();
        emptySet.add(null);

        return CollectionUtils.isNotEmpty(collection)
                ? collection
                : emptySet;
    }
}
