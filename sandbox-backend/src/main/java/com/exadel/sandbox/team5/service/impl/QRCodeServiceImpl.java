package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.barcodes.QRCode;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.service.QRCodeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
@Log4j2
public class QRCodeServiceImpl implements QRCodeService {

    private final OrderDAO orderDAO;
    private final DiscountDAO discountDAO;

    @Override
    public byte[] generateQRCode(String uuid) {
        try (var baos = new ByteArrayOutputStream()) {
            String qrParams = generateQRUrl(uuid);
            var image = QRCode.generateQRCodeImage(qrParams);
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

    @Override
    public boolean checkUUID(String uuid) {
        if (orderDAO.getEmployeePromocodeByUUID(uuid) == null) {
            return false;
        }
        return orderDAO.getEmployeePromocodeByUUID(uuid).equals(uuid);
    }

    @Override
    public boolean ifQRCodeIsValid(String uuid) {
        return checkUUID(uuid)
                && orderDAO.getPromoCodeStatusByUUID(uuid)
                && orderDAO.getPromocodePeriodEndByUUID(uuid).compareTo(new Date()) > 0;
    }

    private String generateQRUrl(String uuid) {
        return "https://sandbox-team5.herokuapp.com/api/orders/validate/"
                + uuid;
    }
}
