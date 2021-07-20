package com.exadel.sandbox.team5.util;

import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.Set;

@NoArgsConstructor
public class QueryUtils {

    public static String getWildcard(String text) {
            return "%" + text + "%";
    }

    public static Set<String> safeCollectionParam(Set<String> collection) {
        return CollectionUtils.isNotEmpty(collection)
                ? collection
                : Collections.emptySet();
    }
}
