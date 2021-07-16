package com.exadel.sandbox.team5.service.export;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class FileNameGenerator {

    public String xlsxFileNameGenerator(String fileName) {
        return "report_" +
                new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) +
                "_" + fileName + " .xlsx";
    }

    public String csvFileNameGenerator(String fileName) {
        return "report_" +
                new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Calendar.getInstance().getTime()) +
                "_" + fileName + " .csv";
    }
}
