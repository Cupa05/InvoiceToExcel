package com.invoicereader.invoiceToExcel.service;

import static com.invoicereader.invoiceToExcel.constants.Constants.*;

import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.springframework.stereotype.Service;

import com.invoicereader.invoiceToExcel.constants.BackGroundColorType;
import com.invoicereader.invoiceToExcel.constants.BackgroundColor;
import com.invoicereader.invoiceToExcel.dto.InvoiceDto;
import com.invoicereader.invoiceToExcel.dto.InvoiceItemsDto;

@Service
public class SummarySheetGenerator {

    public void createSummarySheet(Workbook workbook,
            InvoiceItemsDto data/* , OffsetDateTime from, OffsetDateTime to */) {
        Sheet summary = workbook.createSheet(SUMMARY_TEXT + getNickName(data.getSeller().getName()));

        // ----------------HEADER
        // 1
        summary.addMergedRegion(CellRangeAddress.valueOf("A1:K1"));
        Row row1 = summary.createRow(0);
        createCell(workbook, row1, 0, "SZÁMLÁK LEKÉRDEZÉSE: "/* + from + " " + to, */, BackGroundColorType.ORANGE);
        // TODO a végpontról jöjjön be egy dátum tól ig érték

        // 2
        summary.addMergedRegion(CellRangeAddress.valueOf("A2:K2"));
        Row row2 = summary.createRow(1);
        createCell(workbook, row2, 0, "SZÁMLÁK", BackGroundColorType.GREEN);

        // 3
        Row row3 = summary.createRow(2);
        createCell(workbook, row3, 0, "Vevő neve", BackGroundColorType.GREEN);
        createCell(workbook, row3, 1, "Vevő email", BackGroundColorType.GREEN);
        createCell(workbook, row3, 2, "Vevő ország", BackGroundColorType.GREEN);
        // TODO többi oszlop

        createBorder(1, 4, 1, 11, BorderStyle.MEDIUM, summary);

        // ----------------DATA
        // 4
        for (int i = 0; i < data.getItems().size(); i++) {
            InvoiceDto currentItem = data.getItems().get(i);
            Row row = summary.createRow(i + 3);
            createCell(workbook, row, 0, currentItem.getCustomer().getName(), BackGroundColorType.ORANGE);
            createCell(workbook, row, 1, currentItem.getCustomer().getEmail(), BackGroundColorType.ORANGE);
            // TODO többi oszlop
        }
        System.out.println(data.getItems().size());
        createBorder(4, 4 + data.getItems().size(), 1, 11, BorderStyle.THIN, summary);

    }

    private String getNickName(String name) {
        List<String> splits = List.of(name.split(SPACE));
        StringBuilder nickName = new StringBuilder();
        for (String split : splits) {
            nickName.append(split.charAt(0));
        }
        return nickName.toString();
    }

    private void createCell(Workbook workbook, Row row, Integer colIndex, String text, BackGroundColorType type) {
        BackgroundColor backgroundColor = getBackGroundColor(type);
        Cell cell = row.createCell(colIndex);
        cell.setCellValue(text);
        cell.setCellStyle(setCellStyle(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,
                backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue()));
    }

    private BackgroundColor getBackGroundColor(BackGroundColorType type) {
        for (BackgroundColor backgroundColor : BACKGROUND_COLORS) {
            if (backgroundColor.getType().equals(type)) {
                return backgroundColor;
            }
        }
        return WHITE;
    }

    private CellStyle setCellStyle(Workbook workbook, HorizontalAlignment horizontalAlignment,
            VerticalAlignment verticalAlignment, int r, int g, int b) {
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle.setAlignment(horizontalAlignment);
        cellStyle.setVerticalAlignment(verticalAlignment);
        cellStyle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) r, (byte) g, (byte) b}, null));
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }

    private void createBorder(int firstRow, int lastRow, int firstColumn, int lastColumn, BorderStyle borderStyle,
            Sheet sheet) {
        for (int row = firstRow; row < lastRow; row++) {
            for (int column = firstColumn; column <= lastColumn; column++) {
                CellRangeAddress region = new CellRangeAddress(row - 1, row - 1, column - 1, column - 1);
                RegionUtil.setBorderRight(borderStyle, region, sheet);
                RegionUtil.setBorderLeft(borderStyle, region, sheet);
                RegionUtil.setBorderTop(borderStyle, region, sheet);
                RegionUtil.setBorderBottom(borderStyle, region, sheet);
            }
        }

    }
}
