package com.plenigo.task.entity;

import lombok.Data;

import java.util.Date;

@Data
public class InvoiceItem {

    private Integer position;
    private String title;
    private String productId;
    private String plenigoOfferId;
    private String plenigoProductId;
    private String plenigoStepId;
    private Double price;
    private Double tax;
    private String taxCountry;
    private Integer quantity;
    private String costCenter;
    private String purchaseNumber;
    private String deliveryCustomerId;
    private InvoiceAddress deliveryAddress;
    private Long subscriptionItemId;
    private Date periodStartDate;
    private Date periodEndDate;
    private Long orderItemId;
    private Integer discountPercentage;
    private Date createdDate;
    private Date changedDate;
    private String taxType;
    private String purchasedAddonId;
    private String plenigoAddonId;
}
