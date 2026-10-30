package com.invoicereader.invoiceToExcel.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String SUMMARY_TEXT = "Összesítő_";

    public static final String SPACE = " ";

    public static final BackgroundColor ORANGE = new BackgroundColor(247, 199, 172, BackGroundColorType.ORANGE);
    public static final BackgroundColor GREEN = new BackgroundColor(198, 239, 206, BackGroundColorType.GREEN);
    public static final BackgroundColor WHITE = new BackgroundColor(255, 255, 255, BackGroundColorType.WHITE);

    public static final List<BackgroundColor> BACKGROUND_COLORS = Arrays.asList(GREEN, ORANGE, WHITE);
}
