package com.exadel.sandbox.team5.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
class ValidatePromoCodeGeneratorTest {

    @InjectMocks
    ValidatePromoCodeGenerator validatePromoCodeGenerator;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateUUID_ReturnString() {
        String result = validatePromoCodeGenerator.generateUUID();

        assertNotNull(result);
        assertEquals(String.class, result.getClass());
    }
}