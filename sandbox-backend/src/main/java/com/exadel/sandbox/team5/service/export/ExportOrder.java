package com.exadel.sandbox.team5.service.export;

import java.io.InputStream;

public interface ExportOrder {

    InputStream ordersByDiscountsCSV();

    InputStream ordersByCategoriesCSV();

    InputStream ordersByDiscountsXLSX();

    InputStream ordersByCategoriesXLSX();
}
