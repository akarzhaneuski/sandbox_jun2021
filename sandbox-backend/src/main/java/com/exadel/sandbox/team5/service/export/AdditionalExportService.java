package com.exadel.sandbox.team5.service.export;

import java.io.InputStream;
import java.util.Map;

public interface AdditionalExportService {

    InputStream additionalExportServiceCSV(Map<String, String> map, String entity, String amount);

    InputStream additionalExportServiceXLSX(Map<String, String> map, String entity, String amount);
}
