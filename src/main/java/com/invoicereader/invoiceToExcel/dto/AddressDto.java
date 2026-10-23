package com.invoicereader.invoiceToExcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String postalCode;
    private String city;
    private String streetAndHouseNumber;
    private String country;
}
