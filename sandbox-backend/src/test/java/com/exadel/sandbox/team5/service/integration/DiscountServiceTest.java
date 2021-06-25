package com.exadel.sandbox.team5.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.exadel.sandbox.team5.BackendApplication;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.service.DiscountService;

@SpringBootTest(classes = BackendApplication.class)
class DiscountServiceTest extends AbstractIntegrationTest {

    @Autowired
    private DiscountService discountService;

    @Test
    @Transactional
    void testGenerateQRCode_if_result_not_null() throws IOException {
        final var bytes = discountService.generateQRCode();

        assertNotEquals(0, bytes.length);
        var bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        assertEquals(BufferedImage.TYPE_BYTE_BINARY, bufferedImage.getType());
    }

    @Test
    @Transactional
    void doSomeTest() {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setName("Some test");

        discountService.save(discountDto);
        assertEquals( 1, discountService.getAll().size());
    }
}
