package com.exadel.sandbox.team5.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ValidatePromoCodeGeneratorTest {

    @InjectMocks
    ValidatePromoCodeGenerator validatePromoCodeGenerator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void generateUUID_ReturnString() {
        String result = validatePromoCodeGenerator.generateUUID();

        assertNotNull(result);
        assertEquals(String.class, result.getClass());
    }
}