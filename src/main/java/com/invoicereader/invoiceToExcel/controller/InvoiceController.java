package com.invoicereader.invoiceToExcel.controller;

import com.invoicereader.invoiceToExcel.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@RestController
public class InvoiceController {
    @GetMapping("/invoice")
    public InvoiceItemsDto invoice() {

        return createInvoiceItemsDto();
    }
    private InvoiceItemsDto createInvoiceItemsDto() {
        InvoiceItemsDto invoiceItemsDto = new InvoiceItemsDto();
        AccountDto seller = new AccountDto();
        seller.setName("Zágoni Ádám");
        seller.setAddress(new AddressDto("2142","Nagytarcsa","Petőfi lakótelep 1","Magyarország"));
        invoiceItemsDto.setSeller(seller);
        invoiceItemsDto.setItems(List.of(createInvoiceDto(), createInvoiceDto()));
        return invoiceItemsDto;
    }
    private InvoiceDto createInvoiceDto() {
        AccountDto customer = new AccountDto();
        customer.setName("Tóth Tamás");
        customer.setAddress(new AddressDto("2143","Kistarcsa","Árpád vezér utca 11.","Magyarország"));

        InvoiceDetailsDto details = new InvoiceDetailsDto();
        details.setInvoiceDate(OffsetDateTime.of(2026,8,24,0,0,0,0, ZoneOffset.UTC));
        details.setPaymentDeadline(OffsetDateTime.of(2026,8,30,0,0,0,0, ZoneOffset.UTC));
        details.setDateOfPayment(OffsetDateTime.of(2026,8,29,0,0,0,0, ZoneOffset.UTC));
        details.setPaymentMode("Utalás");

        PricingDto pricing = new PricingDto();
        pricing.setCurrency("HUF");
        pricing.setNetAmount(10000.0);

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceNumber("2026-29");
        invoiceDto.setCustomer(customer);
        invoiceDto.setDetails(details);
        invoiceDto.setPricing(pricing);
        return invoiceDto;
    }
}
