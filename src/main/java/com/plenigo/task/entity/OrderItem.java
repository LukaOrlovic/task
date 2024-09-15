package com.plenigo.task.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OrderItem {

    private int position;
    private String productId;
    private String plenigoOfferId;
    private String plenigoProductId;
    private String plenigoStepId;
    private String accessRightUniqueId;
    private String title;
    private String internalTitle;
    private String taxType;
    private double price;
    private double tax;
    private String taxCountry;
    private int quantity;
    private int discountPercentage;
    private String costCenter;
    private String purchaseNumber;
    private String voucherCode;
    private String deliveryCustomerId;
    private OrderAddress deliveryAddress;
    private long subscriptionItemId;
    private boolean externalBilling;
    private String taxState;
    private long purchasedAddonId;
    private Date validityEndDate;
    private VoucherPurchaseData voucherPurchase;
    private Date createdDate;
    private Date changedDate;
    private String plenigoAddonId;
    private String creditCount;
    private String creditWalletUniqueId;
    private String connectedOfferInfo;
}
