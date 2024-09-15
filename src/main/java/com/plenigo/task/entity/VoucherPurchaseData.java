package com.plenigo.task.entity;

import lombok.Data;

import java.util.Date;

@Data
public class VoucherPurchaseData {

    private long voucherTemplateId;  // required
    private String voucherCode;
    private Date voucherEndDate;
}
