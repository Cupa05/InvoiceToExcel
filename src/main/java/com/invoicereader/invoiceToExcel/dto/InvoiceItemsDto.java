package com.invoicereader.invoiceToExcel.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemsDto {
    private AccountDto seller;
    private List<InvoiceDto> items;
}
