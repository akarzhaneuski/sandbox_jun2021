package com.exadel.sandbox.team5.util;

import java.util.ArrayList;
import java.util.List;

import com.exadel.sandbox.team5.entity.Discount;
import org.springframework.data.jpa.domain.Specification;


public final class DiscountSpecificationsBuilder {

    private final List<SpecSearchCriteria> params;

    public DiscountSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    // API

    public final DiscountSpecificationsBuilder with(final String key, final String operation,
                                                    final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final DiscountSpecificationsBuilder with(final String orPredicate, final String key, final String operation,
                                                    final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<Discount> build() {
        if (params.size() == 0)
            return null;

        Specification<Discount> result = new DiscountSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new DiscountSpecification(params.get(i)))
                    : Specification.where(result).and(new DiscountSpecification(params.get(i)));
        }

        return result;
    }

    public final DiscountSpecificationsBuilder with(DiscountSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final DiscountSpecificationsBuilder with(SpecSearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}