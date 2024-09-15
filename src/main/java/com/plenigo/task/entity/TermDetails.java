package com.plenigo.task.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TermDetails {

    private Long termsId;
    private String uniqueId;
    private Date acceptanceDate;
}
