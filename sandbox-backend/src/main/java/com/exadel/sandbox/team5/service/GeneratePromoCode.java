package com.exadel.sandbox.team5.service;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GeneratePromoCode {
    public String UUIDgenerator() {
        return UUID.randomUUID().toString();
    }
}
