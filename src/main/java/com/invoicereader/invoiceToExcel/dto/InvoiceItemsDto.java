package com.invoicereader.invoiceToExcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemsDto {
    private AccountDto seller;
    private List<InvoiceDto> items;
}
