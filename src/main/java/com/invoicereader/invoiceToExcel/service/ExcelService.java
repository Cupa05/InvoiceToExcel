package com.invoicereader.invoiceToExcel.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ExcelService {

    public byte[] generateExcel() throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet s1 = workbook.createSheet("2026");
            Row row = s1.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Valami");


            workbook.write(out);
            return out.toByteArray();
        }
    }
}
