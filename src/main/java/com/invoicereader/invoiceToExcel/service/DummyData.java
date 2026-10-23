package com.invoicereader.invoiceToExcel.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.stereotype.Service;

import com.invoicereader.invoiceToExcel.dto.*;

@Service
public class DummyData {

    public InvoiceItemsDto create() {
        return createInvoiceItemsDto();
    }

    private InvoiceItemsDto createInvoiceItemsDto() {
        return createInvoiceItems();
    }

    private InvoiceItemsDto createInvoiceItems() {
        AccountDto seller = createAccount("Zágoni Ádám", "z.á@gmail.com", "2142", "Nagytarcsa", "Petőfi lakótelep 1",
                "Magyarország");

        AccountDto customer1 = createAccount("AB", "a.b@gmail.com", "254500", "ASD", "Petőfi 1", "Magyarország");
        InvoiceDto invoice1 = createInvoice("2026-123", customer1, createInvoiceDetails(), createPricing(1234.0));

        AccountDto customer2 = createAccount("BA", "b.a@gmail.com", "21232", "gsdfdsf", "fsdfsdfsdf 1", "Magyarország");
        InvoiceDto invoice2 = createInvoice("2026-3", customer2, createInvoiceDetails(), createPricing(144234.0));

        InvoiceItemsDto invoiceItemsDto = new InvoiceItemsDto();
        invoiceItemsDto.setSeller(seller);
        invoiceItemsDto.setItems(List.of(invoice1, invoice2));
        return invoiceItemsDto;
    }

    private InvoiceDto createInvoice(String invoiceNumber, AccountDto customer, InvoiceDetailsDto details,
            PricingDto pricing) {
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceNumber(invoiceNumber);
        invoiceDto.setCustomer(customer);
        invoiceDto.setDetails(details);
        invoiceDto.setPricing(pricing);
        return invoiceDto;
    }

    private AccountDto createAccount(String name, String email, String postalCode, String city,
            String streetAndHouseNumber, String country) {
        AccountDto customer = new AccountDto();
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(new AddressDto(postalCode, city, streetAndHouseNumber, country));
        return customer;
    }

    private InvoiceDetailsDto createInvoiceDetails() {
        InvoiceDetailsDto details = new InvoiceDetailsDto();
        details.setInvoiceDate(OffsetDateTime.of(2026, 8, 24, 0, 0, 0, 0, ZoneOffset.UTC));
        details.setPaymentDeadline(OffsetDateTime.of(2026, 8, 30, 0, 0, 0, 0, ZoneOffset.UTC));
        details.setDateOfPayment(OffsetDateTime.of(2026, 8, 29, 0, 0, 0, 0, ZoneOffset.UTC));
        details.setPaymentMode("Utalás");
        return details;
    }

    private PricingDto createPricing(Double price) {
        PricingDto pricing = new PricingDto();
        pricing.setCurrency("HUF");
        pricing.setNetAmount(price);
        return pricing;
    }
}
