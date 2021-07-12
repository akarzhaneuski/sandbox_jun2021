package com.exadel.sandbox.team5.service.export;

import java.io.InputStream;
import java.util.Map;

public interface ExportService {

    InputStream exportServiceCSV(Map<String, String> map, String entity, String amount);

    InputStream exportServiceXLSX(Map<String, String> map, String entity, String amount);
}
