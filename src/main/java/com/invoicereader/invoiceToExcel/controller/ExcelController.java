package com.invoicereader.invoiceToExcel.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoicereader.invoiceToExcel.service.ExcelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateExcel(
    // TODO
    // @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") OffsetDateTime
    // from,
    // @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") OffsetDateTime to
    ) throws IOException {
        byte[] excelBytes = excelService.generateExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "report.xlsx");
        headers.setContentLength(excelBytes.length);

        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
    }
}
