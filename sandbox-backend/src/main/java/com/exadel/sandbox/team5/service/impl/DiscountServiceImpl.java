package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.barcodes.QRCodeGenerator;
import com.exadel.sandbox.team5.dao.DiscountDAO;
import com.exadel.sandbox.team5.dao.ReviewDAO;
import com.exadel.sandbox.team5.dto.DiscountDto;
import com.exadel.sandbox.team5.entity.Discount;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@Service
@RequiredArgsConstructor
@Log4j2
public class DiscountServiceImpl implements DiscountService {

    private final DiscountDAO discountDAO;
    private final MapperConverter mapper;
    private final ReviewDAO reviewDAO;

    @Override
    public DiscountDto getById(Long id) {
        var discountDto = discountDAO.findById(id)
                .map(discount -> mapper.map(discount, DiscountDto.class))
                .orElseThrow(NoSuchElementException::new);
        setAverageRate(discountDto);
        return discountDto;
    }

    private void setAverageRate(DiscountDto discountDto) {
        discountDto.setRate(reviewDAO.findRate(discountDto.getId()));
    }

    @Override
    public List<DiscountDto> getAll() {
        List<DiscountDto> resultWithoutRage = mapper.mapAll(discountDAO.findAll(), DiscountDto.class);
        resultWithoutRage.forEach(this::setAverageRate);
        return resultWithoutRage;
    }

    @Override
    public DiscountDto save(DiscountDto discount) {
        Discount dis = mapper.map(discount, Discount.class);
        return mapper.map(discountDAO.saveAndFlush(dis), DiscountDto.class);
    }

    @Override
    public DiscountDto update(DiscountDto discount) {
        return this.save(discount);
    }

    @Override
    public void delete(Long id) {
        discountDAO.deleteById(id);
    }

    @Override
    public byte[] generateQRCode() {
        try (var baos = new ByteArrayOutputStream()) {
            var image = QRCodeGenerator.generateQRCodeImage("Exadel employee. Special discount");
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("There was an error during barcode generation", e);
            throw new NoSuchElementException(e);
        }
    }
}
