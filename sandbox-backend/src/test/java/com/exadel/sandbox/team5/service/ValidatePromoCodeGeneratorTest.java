package com.exadel.sandbox.team5.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ValidatePromoCodeGeneratorTest {

    @InjectMocks
    ValidatePromoCodeGenerator validatePromoCodeGenerator;

    @Test
    void generateUUID_ReturnString() {
        String result = validatePromoCodeGenerator.generateUUID();

        assertNotNull(result);
        assertEquals(String.class, result.getClass());
    }
}