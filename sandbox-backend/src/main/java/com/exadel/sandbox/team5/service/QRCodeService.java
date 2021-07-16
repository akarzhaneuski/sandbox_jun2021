package com.exadel.sandbox.team5.service;

import java.io.File;

public interface QRCodeService {
    /**
     * Generate QR code.
     * @return byte array as QR code image representation.
     */
    byte[] generateQRCode(String promocode);

    /**
     * Read QR code.
     * @return String text from image.
     */
    String readQRCode(File file);

    /**
     * @return true if uuid and promocode exists in DB
     */
    String validateQR(String uuid);
}
