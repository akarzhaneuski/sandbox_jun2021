package com.exadel.sandbox.team5.service.export;

import com.exadel.sandbox.team5.util.CSVConvertor;
import com.exadel.sandbox.team5.util.XLSXConvertor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

@Component
public class ExportImpl implements ExportService {

    @Override
    public InputStream exportServiceCSV(Map<String, String> map, String entity, String amount) {
        return CSVConvertor.createCSVFile(map, entity, amount);
    }

    @Override
    public InputStream exportServiceXLSX(Map<String, String> map, String entity, String amount) {
        return XLSXConvertor.createXLSXFile(map, entity, amount);
    }
}
