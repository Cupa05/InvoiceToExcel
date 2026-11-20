package com.invoicereader.invoiceToExcel.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.OffsetDateTime;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.invoicereader.invoiceToExcel.dto.InvoiceItemsDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final DummyData dummyData;

    private final SummarySheetGenerator summarySheetGenerator;

    public byte[] generateExcel(OffsetDateTime from, OffsetDateTime to) throws IOException {
        InvoiceItemsDto data = dummyData.create();
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            InvoiceItemsDto filteredData = getFilteredData(data, from, to);
            summarySheetGenerator.createSummarySheet(workbook, filteredData);
            // TODO n darab személy számlái külön munkalapon

            workbook.write(out);
            return out.toByteArray();
        }
    }

    // TODO csak azok a datak menjenek tovább amik az adott szűrési feltételeknek
    // eleget adnak
    private InvoiceItemsDto getFilteredData(InvoiceItemsDto data, OffsetDateTime from, OffsetDateTime to) {
        // TODO ha csak from értéket ad meg a felhasználó, akkor csak az adott dátum
        // utáni számlákat listázza ki a program
        // TODO ha csak to értéket ad meg a felhasználó, akkor csak az adott dátum
        // előtti számlákat listázza ki a program
        // TODO ha from és to értéket ad meg a felhasználó, akkor csak az adott dátum
        // közötti számlákat listázza ki a program
        // TODO ha nem ad se to és se from értéket a felhasználó, akkor az összes
        // számlát listázza ki a program
        InvoiceItemsDto filteredData = data;
        filteredData.setFilteredInvoiceString("valami"); // from to érték beleírása // TODO a formátum szép legyen az
                                                         // excelben
        return filteredData;
    }

}
