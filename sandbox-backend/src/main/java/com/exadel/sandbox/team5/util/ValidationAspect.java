package com.exadel.sandbox.team5.util;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;

@Aspect
@Component
@AllArgsConstructor
public class ValidationAspect {

    private final Validator validator;

    @AfterReturning(pointcut = "@annotation(com.exadel.sandbox.team5.annotations.Validate)", returning = "result")
    public void validate(JoinPoint joinPoint, Object result) {

        Set<ConstraintViolation<Object>> violations = validator.validate(result);
        if (!violations.isEmpty()) {
            var builder = new StringBuilder();
            violations.forEach(violation -> builder
                    .append(violation.getPropertyPath())
                    .append(violation.getMessage()));
            throw new ValidationException("Invalid values for fields: " + builder);
        }
    }
}
