package com.plenigo.task.services.csv;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CsvItem {

    private long orderId;
    private int orderItemPosition;
    private String orderItemTitle;
    private double orderItemPrice;
    private double orderItemTax;
    private String customerId;
    private String customerEmail;
    private Date customerCreateDate;
    private Long invoiceId;
    private Date invoiceCreateDate;
}
