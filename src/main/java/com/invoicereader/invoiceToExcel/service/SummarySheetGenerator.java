package com.invoicereader.invoiceToExcel.service;

import static com.invoicereader.invoiceToExcel.constants.Constants.SPACE;
import static com.invoicereader.invoiceToExcel.constants.Constants.SUMMARY_TEXT;

import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import com.invoicereader.invoiceToExcel.dto.InvoiceDto;
import com.invoicereader.invoiceToExcel.dto.InvoiceItemsDto;

@Service
public class SummarySheetGenerator {

    public void createSummarySheet(Workbook workbook,
            InvoiceItemsDto data/* , OffsetDateTime from, OffsetDateTime to */) {
        Sheet summary = workbook.createSheet(SUMMARY_TEXT + getNickName(data.getSeller().getName()));

        // 1
        summary.addMergedRegion(CellRangeAddress.valueOf("A1:K1"));
        Row row1 = summary.createRow(0);
        createCell(workbook, row1, 0, "SZÁMLÁK LEKÉRDEZÉSE: "/* + from + " " + to, */, IndexedColors.ORANGE);
        // TODO a végpontról jöjjön be egy dátum tól ig érték

        // 2
        summary.addMergedRegion(CellRangeAddress.valueOf("A2:K2"));
        Row row2 = summary.createRow(1);
        createCell(workbook, row2, 0, "SZÁMLÁK", IndexedColors.GREEN);

        // 3
        Row row3 = summary.createRow(2);
        createCell(workbook, row3, 0, "Vevő neve", IndexedColors.GREEN);
        createCell(workbook, row3, 1, "Vevő email", IndexedColors.GREEN);
        createCell(workbook, row3, 2, "Vevő ország", IndexedColors.GREEN);
        // TODO többi oszlop

        // 4 --> Adatok kiírása
        for (int i = 0; i < data.getItems().size(); i++) {
            InvoiceDto currentItem = data.getItems().get(i);
            Row row = summary.createRow(i + 3);
            createCell(workbook, row, 0, currentItem.getCustomer().getName(), IndexedColors.ORANGE);
            createCell(workbook, row, 1, currentItem.getCustomer().getEmail(), IndexedColors.ORANGE);
            // TODO többi oszlop
        }

    }

    private String getNickName(String name) {
        List<String> splits = List.of(name.split(SPACE));
        StringBuilder nickName = new StringBuilder();
        for (String split : splits) {
            nickName.append(split.charAt(0));
        }
        return nickName.toString();
    }

    private CellStyle setCellStyle(Workbook workbook, HorizontalAlignment horizontalAlignment,
            VerticalAlignment verticalAlignment, IndexedColors color) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(horizontalAlignment);
        cellStyle.setVerticalAlignment(verticalAlignment);
        cellStyle.setFillForegroundColor(color.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }

    private void createCell(Workbook workbook, Row row, Integer colIndex, String text, IndexedColors color) {
        Cell cell = row.createCell(colIndex);
        cell.setCellValue(text);
        cell.setCellStyle(setCellStyle(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, color));
    }
}
