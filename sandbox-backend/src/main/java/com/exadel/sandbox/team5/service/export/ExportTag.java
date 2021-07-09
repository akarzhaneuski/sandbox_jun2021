package com.exadel.sandbox.team5.service.export;

import java.io.InputStream;

public interface ExportTag {

    InputStream ordersByTagCSV();

    InputStream ordersByTagXLSX();
}
