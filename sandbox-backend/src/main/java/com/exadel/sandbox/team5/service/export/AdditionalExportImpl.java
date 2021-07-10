package com.exadel.sandbox.team5.service.export;

import com.exadel.sandbox.team5.service.convertor.Convertor;

import java.io.InputStream;
import java.util.Map;

public class AdditionalExportImpl implements AdditionalExportService{

    @Override
    public InputStream exportServiceCSV(Map<String, String> map, String entity, String amount) {
        return Convertor.createCSVFile(map, entity, amount);
    }

    @Override
    public InputStream exportServiceXLSX(Map<String, String> map, String entity, String amount) {
        return Convertor.createXLSXFile(map, entity, amount);
    }
    @Override
    public InputStream additionalExportServiceCSV(Map<String, String> map, String entity, String amount) {
        return Convertor.createCSVFile(map, entity, amount);
    }

    @Override
    public InputStream additionalExportServiceXLSX(Map<String, String> map, String entity, String amount) {
        return Convertor.createXLSXFile(map, entity, amount);
    }
}
