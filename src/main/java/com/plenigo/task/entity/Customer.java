package com.plenigo.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String customerId;
    private String registrationSource;
    private Date registrationDate;
    private boolean ageVerificationPinEnabled;
    private String status;
    private String[] ssoLoginProviders;
    private String[] customerMarks;
    private String username;
    private String email;
    private String externalSystemId;
    private String salutation;
    private String firstName;
    private String lastName;
    private String invoiceEmail;
    private String language;
    private String mobileNumber;
    private Date birthday;
    private MiscelllaneousData miscellaneousData;
    private String createdBy;
    private String createdByType;
    private String changedBy;
    private String changedByType;
    private Date createdDate;
    private Date changedDate;
    private boolean twoFactorEnabled;
    private Map<String, TermDetails> acceptedTerms;

}
