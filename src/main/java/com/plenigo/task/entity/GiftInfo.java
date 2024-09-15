package com.plenigo.task.entity;

import lombok.Data;

@Data
public class GiftInfo {

    private boolean notifyGiftee;
    private String donorText;
    private String gifteeEmail;
}
