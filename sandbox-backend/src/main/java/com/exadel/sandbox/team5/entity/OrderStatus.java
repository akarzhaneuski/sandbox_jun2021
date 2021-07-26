package com.exadel.sandbox.team5.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    VALID("Valid"),
    INVALID("Invalid");

    private String text;
}
