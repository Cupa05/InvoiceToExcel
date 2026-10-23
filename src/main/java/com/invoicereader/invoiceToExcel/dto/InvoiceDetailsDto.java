package com.invoicereader.invoiceToExcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsDto {
    private OffsetDateTime invoiceDate;
    private OffsetDateTime paymentDeadline;
    private OffsetDateTime dateOfPayment;
    private String paymentMode; // TODO: Enum kiszervezés.
}
