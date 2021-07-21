package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.barcodes.QRCode;
import com.exadel.sandbox.team5.dao.OrderDAO;
import com.exadel.sandbox.team5.service.OrderService;
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
    private final OrderService orderService;

    @Override
    public byte[] generateQRCode(String uuid) {
        try (var baos = new ByteArrayOutputStream()) {
            String qrParams = generateQRUrl(uuid);
            var image = QRCode.generateQRCodeImage(qrParams);
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            throw new NoSuchElementException("There was an error during barcode generation");
        }
    }

    @SneakyThrows
    @Override
    public String readQRCode(File file) {
        return QRCode.readQRCodeImage(file);
    }

    @Override
    public String validateQR(String uuid) {
        var notValid = "Not valid";
        var responseMessage = "\"%s\" \"%s\"";
        if (!uuid.equals(orderDAO.getEmployeePromocodeByUUID(uuid))) {
            return String.format(responseMessage, notValid, "Order not found. Promocode is incorrect");
        }
        if (!orderDAO.getPromoCodeStatusByUUID(uuid)) {
            return String.format(responseMessage, notValid, "Order has already been activated");
        }
        if (orderDAO.getPromocodePeriodEndByUUID(uuid).compareTo(new Date()) < 0) {
            return String.format(responseMessage, notValid, "Order has expired");
        }
        orderService.invalidatePromoCode(uuid);
        return String.format(responseMessage, "Valid", orderDAO.getUserLoginByOrderUUID(uuid));
    }

    private String generateQRUrl(String uuid) {
        return "https://sandbox-team5.herokuapp.com/api/orders/validate/" + uuid;
    }
}
