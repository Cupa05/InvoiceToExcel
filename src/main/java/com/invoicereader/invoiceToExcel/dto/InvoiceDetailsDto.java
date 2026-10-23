package com.invoicereader.invoiceToExcel.dto;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsDto {
    private OffsetDateTime invoiceDate;
    private OffsetDateTime paymentDeadline;
    private OffsetDateTime dateOfPayment;
    private String paymentMode; // TODO: Enum kiszervezés.
}
