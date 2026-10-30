package com.invoicereader.invoiceToExcel.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BackgroundColor {
    private Integer red, green, blue;
    private BackGroundColorType type;
}
