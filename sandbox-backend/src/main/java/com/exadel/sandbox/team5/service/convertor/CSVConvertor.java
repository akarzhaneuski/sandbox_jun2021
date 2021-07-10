package com.exadel.sandbox.team5.service.convertor;

import lombok.experimental.UtilityClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@UtilityClass
public class CSVConvertor {

    public static InputStream createCSVFile(Map<String, String> mapEntry, String statisticEntity, String amount) {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader(statisticEntity, amount));) {

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
