package com.exadel.sandbox.team5.service.convertor;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CSVConvertor {

    public ByteArrayInputStream createFile(Map<String, String> mapEntry) {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT);) {

            for (Map.Entry<String, String> entity : mapEntry.entrySet()) {
                List<String> data = Arrays.asList(
                        entity.getKey(),
                        entity.getValue());
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
