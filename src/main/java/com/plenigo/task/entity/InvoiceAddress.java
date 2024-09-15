package com.plenigo.task.entity;

import lombok.Data;

import java.util.Date;

@Data
public class InvoiceAddress {

    private Boolean businessAddress;
    private String salutation;
    private String title;
    private String firstName;
    private String lastName;
    private String companyName;
    private String additionalCompanyInfo;
    private String street;
    private String streetNumber;
    private String additionalStreetInfo;
    private String postbox;
    private String postcode;
    private String city;
    private String state;
    private String country;
    private String vatNumber;
    private String phoneNumber;
    private Date createdDate;
    private Date changedDate;
    private String type;
}
