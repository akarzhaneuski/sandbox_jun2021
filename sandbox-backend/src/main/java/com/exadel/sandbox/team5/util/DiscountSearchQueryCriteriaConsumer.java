package com.exadel.sandbox.team5.util;

import java.util.function.Consumer;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DiscountSearchQueryCriteriaConsumer implements Consumer<Filter>{

    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root r;

    public DiscountSearchQueryCriteriaConsumer(Predicate predicate, CriteriaBuilder builder, Root r) {
        super();
        this.predicate = predicate;
        this.builder = builder;
        this.r= r;
    }

    @Override
    public void accept(Filter param) {

        if (param.getOperation().equalsIgnoreCase(">")) {
            predicate = builder.and(predicate, builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
        } else if (param.getOperation().equalsIgnoreCase("<")) {
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
        } else if (param.getOperation().equalsIgnoreCase(":")) {
            if (r.get(param.getKey()).getJavaType() == String.class) {
                predicate = builder.and(predicate, builder.like(r.get(param.getKey()), "%" + param.getValue() + "%"));
            } else {
                predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), param.getValue()));
            }
        }
    }
    public Predicate getPredicate() {
        return predicate;
    }
}
