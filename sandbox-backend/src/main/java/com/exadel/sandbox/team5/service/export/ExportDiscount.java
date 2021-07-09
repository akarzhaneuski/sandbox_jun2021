package com.exadel.sandbox.team5.service.export;

import java.io.InputStream;

public interface ExportDiscount {

    InputStream viewsByDiscountsCSV();

    InputStream viewsByDiscountsXLSX();
}
