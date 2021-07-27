package com.exadel.sandbox.team5.util;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import javax.validation.Validator;

@Aspect
@Component
@AllArgsConstructor
public class ValidationAspect {

    private final Validator validator;

    @Before("@within(org.springframework.stereotype.Service)")
    public void getFromServiceInfo(JoinPoint joinPoint) {
        for (Object object : joinPoint.getArgs()) {
            var results = validator.validate(object);
            if (!results.isEmpty()) {
                var builder = new StringBuilder();
                results.forEach(result -> builder
                        .append(result.getPropertyPath())
                        .append(result.getMessage())
                        .append("; ")
                );
                throw new ValidationException("Invalid values for fields: " + builder);
            }
        }
    }
}
