package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.barcodes.QRCode;
import com.exadel.sandbox.team5.service.QRCodeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
@Log4j2
public class QRCodeServiceImpl implements QRCodeService {

    @Override
    public byte[] generateQRCode(String promocode) {
        try (var baos = new ByteArrayOutputStream()) {
            var image = QRCode.generateQRCodeImage(promocode);
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("There was an error during barcode generation", e);
            throw new NoSuchElementException(e);
        }
    }

    @SneakyThrows
    @Override
    public String readQRCode(File file) {
        return QRCode.readQRCodeImage(file);
    }
}
