package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.dto.DiscountDto;

public interface DiscountService extends CRUDService<DiscountDto> {

    /**
     * Generate QR code.
     * @return byte array as QR code image representation.
     */
    byte[] generateQRCode();
}
