package com.exadel.sandbox.team5.util;

import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@UtilityClass
public class XLSXConvertor {

    public static InputStream createXLSXFile(Map<String, String> mapEntry, String statisticEntity, String amount) {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Workbook writeBook = new XSSFWorkbook();
            Sheet sheet = writeBook.createSheet();

            int rowCount = 0;
            Row row = sheet.createRow(rowCount);
            Cell cell = row.createCell(0);
            cell.setCellValue(statisticEntity);
            row.createCell(1).setCellValue(amount);

            for (Map.Entry<String, String> entity : mapEntry.entrySet()) {
                row = sheet.createRow(++rowCount);
                cell = row.createCell(0);
                cell.setCellValue(entity.getKey());
                row.createCell(1).setCellValue(entity.getValue());
            }

            writeBook.write(out);

            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to XLSX file: " + e.getMessage());
        }
    }
}
