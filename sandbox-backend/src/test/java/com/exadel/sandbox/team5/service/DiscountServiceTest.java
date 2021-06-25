package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.impl.DiscountServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceTest {

    @Mock
    private DiscountDAO discountDAO;

    @Mock
    private MapperConverter mapperConverter;

    @Mock
    private ReviewDAO reviewDAO;

    @InjectMocks
    private DiscountServiceImpl discountService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateQRCode_if_result_not_null() throws IOException {
        final var bytes = discountService.generateQRCode();

        assertNotEquals(0, bytes.length);
        var bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        assertEquals(BufferedImage.TYPE_BYTE_BINARY, bufferedImage.getType());
    }
}
