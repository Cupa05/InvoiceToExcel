package com.invoicereader.invoiceToExcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String name;
    private String email;
    private AddressDto address;
}
