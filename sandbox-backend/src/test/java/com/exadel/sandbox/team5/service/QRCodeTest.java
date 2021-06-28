package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.barcodes.QRCode;
import com.exadel.sandbox.team5.service.impl.QRCodeServiceImpl;
import com.google.zxing.WriterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class QRCodeTest {

    @InjectMocks
    private QRCodeServiceImpl service;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateQRCode_if_type_correct() throws IOException {
        final var bytes = service.generateQRCode();

        assertNotEquals(0, bytes.length);
        var bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
        assertEquals(BufferedImage.TYPE_BYTE_BINARY, bufferedImage.getType());
    }

    @Test
    void testGenerateQRCode_if_text_is_correct() throws IOException, WriterException {
        ImageIO.write(QRCode.generateQRCodeImage("This is just a test"), "png",
                new File("src/test/java/resources/testQR1.png"));

        var qrCode = service.readQRCode(new File("src/test/java/resources/testQR1.png"));

        assertEquals("This is just a test", qrCode);
    }
}
