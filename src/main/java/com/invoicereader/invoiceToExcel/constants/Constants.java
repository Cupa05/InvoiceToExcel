package com.invoicereader.invoiceToExcel.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String SUMMARY_TEXT = "Összesítő_";

    public static final String SPACE = " ";
    public static final String COMMA = ",";

    public static final String DATE_FORMAT = "yyyy.MM.dd.";

    private static final String CUSTOMER_NAME = "Vevő neve";
    private static final String CUSTOMER_EMAIL = "Vevő email";
    private static final String CUSTOMER_COUNTRY = "Vevő ország";
    private static final String CUSTOMER_ADDRESS = "Vevő címe";
    private static final String INVOICE_NUMBER = "Számla sorszáma";
    private static final String INVOICE_DATE = "Számla kelte";
    private static final String PAYMENT_DEADLINE = "Fizetési határidő";
    private static final String DATE_OF_PAYMENT = "Teljesítés kelte";
    private static final String PAYMENT_MODE = "Fizetési mód";
    private static final String NET_AMOUNT = "Nettó összeg";
    private static final String CURRENCY = "Deviza";

    public static final List<String> HEADER = Arrays.asList(CUSTOMER_NAME, CUSTOMER_EMAIL, CUSTOMER_COUNTRY,
            CUSTOMER_ADDRESS, INVOICE_NUMBER, INVOICE_DATE, PAYMENT_DEADLINE, DATE_OF_PAYMENT, PAYMENT_MODE, NET_AMOUNT,
            CURRENCY);

    public static final short HEADER_HEIGHT = (short) (20 * 30);
    public static final short DATA_HEIGHT = (short) (20 * 25);
    public static final short COLUMN_WIDTH = 256 * 30;

    public static final BackgroundColor ORANGE = new BackgroundColor(247, 199, 172, BackGroundColorType.ORANGE);
    public static final BackgroundColor GREEN = new BackgroundColor(198, 239, 206, BackGroundColorType.GREEN);
    public static final BackgroundColor WHITE = new BackgroundColor(255, 255, 255, BackGroundColorType.WHITE);

    public static final List<BackgroundColor> BACKGROUND_COLORS = Arrays.asList(GREEN, ORANGE, WHITE);
}
