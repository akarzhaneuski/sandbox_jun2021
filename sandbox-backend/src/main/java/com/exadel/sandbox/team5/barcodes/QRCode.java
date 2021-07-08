package com.exadel.sandbox.team5.barcodes;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class QRCode {

    public static BufferedImage generateQRCodeImage(String barcodeText) throws WriterException {
        var barcodeWriter = new QRCodeWriter();
        var bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 250, 250);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static String readQRCodeImage(File file)
            throws ChecksumException, NotFoundException, FormatException, IOException {
        var encodedBufferedImage = ImageIO.read(file);
        LuminanceSource source = new BufferedImageLuminanceSource(encodedBufferedImage);
        var result = new QRCodeReader().decode(new BinaryBitmap(new HybridBinarizer(source)));
        return result.getText();
    }

    public static String generateQRUrl(String uuid) {
        var expirationTime = 604800000L; // 7 days
        var qrValidatePeriod = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + expirationTime;
        return "https://sandbox-team5.herokuapp.com/api/orders/validate/"
                + uuid + "/" + qrValidatePeriod;
    }
}
