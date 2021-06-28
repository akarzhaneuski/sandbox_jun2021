package com.exadel.sandbox.team5.service;

import java.io.File;

public interface QRCodeService {

    /**
     * Generate QR code.
     * @return byte array as QR code image representation.
     */
    byte[] generateQRCode();

    /**
     * Read QR code.
     * @return String text from image.
     */
    String readQRCode(File file);
}
