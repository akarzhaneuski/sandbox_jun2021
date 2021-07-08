package com.exadel.sandbox.team5.service.impl;

import com.exadel.sandbox.team5.dao.TagDAO;
import com.exadel.sandbox.team5.dto.TagDto;
import com.exadel.sandbox.team5.entity.Tag;
import com.exadel.sandbox.team5.mapper.MapperConverter;
import com.exadel.sandbox.team5.service.OrderService;
import com.exadel.sandbox.team5.service.TagService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
@Transactional
public class TagServiceImpl extends CRUDServiceDtoImpl<TagDAO, Tag, TagDto> implements TagService {
    private final OrderService orderService;

    public TagServiceImpl(TagDAO tagDAO, MapperConverter mapper, OrderService orderService) {
        super(tagDAO, Tag.class, TagDto.class, mapper);
        this.orderService = orderService;
    }

    @Override
    public void createFile(String excelFilePath) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Map<String, String> ordersByTags = orderService.getOrdersByTags();

        int rowCount = 0;

        for (Map.Entry<String, String> entity: ordersByTags.entrySet()) {
            Row row = sheet.createRow(++rowCount);
            writeBook(entity, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }

    }

    private void writeBook(Map.Entry<String, String> entity, Row row) {
        Cell cell = row.createCell(1);
        cell.setCellValue(entity.getKey());

        cell = row.createCell(2);
        cell.setCellValue(entity.getValue());
    }
}
