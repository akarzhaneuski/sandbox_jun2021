package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.BackendApplication;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
class DiscountServiceTest{

    @Autowired
    private DiscountService discountService;

    @Test
    void testGenerateQRCode_if_result_not_null() throws IOException {
        final var bytes = discountService.generateQRCode();

        assertNotEquals(0, bytes.length);
        var bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        assertEquals(BufferedImage.TYPE_BYTE_BINARY, bufferedImage.getType());
    }
}
