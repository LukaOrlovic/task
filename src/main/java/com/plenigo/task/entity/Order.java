package com.plenigo.task.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {

    private long orderId;
    private String externalSystemId;
    private String status;
    private String type;
    private Date orderDate;
    private double accumulatedPrice;
    private String currency;
    private String paymentMethod;
    private long paymentMethodId;
    private PaymentMethodDetails paymentMethodDetails;
    private String purchaseOrderIndicator;
    private String invoiceCustomerId;
    private boolean analogInvoice;
    private boolean managedExternal;
    private boolean suppressInvoiceSending;
    private GiftInfo giftInfo;
    private OrderAddress invoiceAddress;
    private List<OrderItem> items;
    private String createdBy;
    private String createdByType;
    private String changedBy;
    private String changedByType;
    private Date createdDate;
    private Date changedDate;
    private String managedBy;


}
