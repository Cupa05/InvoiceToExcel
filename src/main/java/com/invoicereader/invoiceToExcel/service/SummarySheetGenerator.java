package com.invoicereader.invoiceToExcel.service;

import static com.invoicereader.invoiceToExcel.constants.Constants.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.springframework.stereotype.Service;

import com.invoicereader.invoiceToExcel.constants.BackGroundColorType;
import com.invoicereader.invoiceToExcel.constants.BackgroundColor;
import com.invoicereader.invoiceToExcel.dto.AddressDto;
import com.invoicereader.invoiceToExcel.dto.InvoiceDto;
import com.invoicereader.invoiceToExcel.dto.InvoiceItemsDto;

@Service
public class SummarySheetGenerator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public void createSummarySheet(Workbook workbook, InvoiceItemsDto data) {
        Sheet summary = workbook.createSheet(SUMMARY_TEXT + getNickName(data.getSeller().getName()));

        // ----------------HEADER
        // 1
        summary.addMergedRegion(CellRangeAddress.valueOf("A1:K1"));
        Row row1 = summary.createRow(0);
        row1.setHeight(HEADER_HEIGHT);
        createCell(workbook, row1, 0, INVOICES_QUERY + data.getFilteredInvoiceString(), BackGroundColorType.ORANGE);

        // 2
        summary.addMergedRegion(CellRangeAddress.valueOf("A2:K2"));
        Row row2 = summary.createRow(1);
        row2.setHeight(HEADER_HEIGHT);
        createCell(workbook, row2, 0, INVOICES, BackGroundColorType.GREEN);

        // 3
        Row row3 = summary.createRow(2);
        row3.setHeight(HEADER_HEIGHT);
        for (int i = 0; i < HEADER.size(); i++) {
            createCell(workbook, row3, i, HEADER.get(i), BackGroundColorType.GREEN);
        }

        createBorder(1, 4, 1, 11, BorderStyle.MEDIUM, summary);

        // ----------------DATA
        // 4
        for (int i = 0; i < data.getItems().size(); i++) {
            InvoiceDto currentItem = data.getItems().get(i);
            AddressDto address = currentItem.getCustomer().getAddress();
            Row row = summary.createRow(i + 3);
            row.setHeight(DATA_HEIGHT);

            createCell(workbook, row, 0, currentItem.getCustomer().getName(), BackGroundColorType.ORANGE);
            createCell(workbook, row, 1, currentItem.getCustomer().getEmail(), BackGroundColorType.ORANGE);
            createCell(workbook, row, 2, address.getCountry(), BackGroundColorType.ORANGE);
            createCell(workbook, row, 3, address.getPostalCode() + SPACE + address.getCity() + COMMA + SPACE
                    + address.getStreetAndHouseNumber(), BackGroundColorType.ORANGE);
            createCell(workbook, row, 4, currentItem.getInvoiceNumber(), BackGroundColorType.ORANGE);
            createCell(workbook, row, 5, currentItem.getDetails().getInvoiceDate().format(FORMATTER),
                    BackGroundColorType.ORANGE);
            createCell(workbook, row, 6, currentItem.getDetails().getPaymentDeadline().format(FORMATTER),
                    BackGroundColorType.ORANGE);
            createCell(workbook, row, 7, currentItem.getDetails().getDateOfPayment().format(FORMATTER),
                    BackGroundColorType.ORANGE);
            createCell(workbook, row, 8, currentItem.getDetails().getPaymentMode(), BackGroundColorType.ORANGE);
            createCell(workbook, row, 9, currentItem.getPricing().getNetAmount().toString(),
                    BackGroundColorType.ORANGE);
            createCell(workbook, row, 10, currentItem.getPricing().getCurrency(), BackGroundColorType.ORANGE);
        }
        createBorder(4, 4 + data.getItems().size(), 1, 11, BorderStyle.THIN, summary);

        for (int i = 0; i < HEADER.size(); i++) {
            summary.setColumnWidth(i, COLUMN_WIDTH);
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
