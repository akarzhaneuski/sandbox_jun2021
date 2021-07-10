package com.exadel.sandbox.team5.service.export;

import java.io.InputStream;

public interface ExportService {

    InputStream ExportServiceCSV();

    InputStream ExportServiceXLSX();
}
