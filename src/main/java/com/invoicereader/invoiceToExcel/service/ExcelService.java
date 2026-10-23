package com.invoicereader.invoiceToExcel.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.invoicereader.invoiceToExcel.dto.InvoiceItemsDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final DummyData dummyData;

    private final SummarySheetGenerator summarySheetGenerator;

    public byte[] generateExcel(/* OffsetDateTime from, OffsetDateTime to */) throws IOException {
        InvoiceItemsDto data = dummyData.create();
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            summarySheetGenerator.createSummarySheet(workbook, data/* , from, to */);
            // TODO n darab személy számlái külön munkalapon

            workbook.write(out);
            return out.toByteArray();
        }
    }

}
