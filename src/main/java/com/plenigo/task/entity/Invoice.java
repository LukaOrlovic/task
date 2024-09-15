package com.plenigo.task.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Invoice {

    private Long invoiceId;

    private Date invoiceDate;

    private String customerEmail;

    private Double accumulatedPrice;

    private String currency;

    private String paymentMethod;

    private Long paymentMethodId;

    private String purchaseOrderIndicator;

    private String invoiceCustomerId;

    private InvoiceAddress invoiceAddress;

    private String transactionId;

    private String type;

    private String status;

    private Boolean paymentChangedToBilling;

    private Long precursorId;

    private Long successorId;

    private Long invoiceCancellationId;

    private String createdBy;

    private String createdByType;

    private String changedBy;

    private String changedByType;

    private Date createdDate;

    private Date changedDate;

    private List<InvoiceItem> items;

    private String analogInvoice;

    private String orderId;

    private String cancellationInvoiceId;
}
