package com.exadel.sandbox.team5.barcodes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class QRCodeGenerator {

    public static BufferedImage generateQRCodeImage(String barcodeText) throws WriterException {
        var barcodeWriter = new QRCodeWriter();
        var bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 250, 250);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
