package com.hws.excelmaker.domain;

import lombok.Data;

@Data
public class ExcelData {

    private String contractDate;
    private String customerName;
    private String belong;
    private String carName;
    private Integer carPrice;
    private String releaseStore;
    private Double charge;
    private String progress;
    private Integer cashBack;
    private String releasePlace;
    private String supportContents;
    private String etcContents;
    private String enrollDate;
    private String carNumber;

    public ExcelData(String contractDate, String customerName, String belong, String carName, Integer carPrice, String releaseStore, Double charge, String progress, Integer cashBack, String releasePlace, String supportContents, String etcContents, String enrollDate, String carNumber) {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.belong = belong;
        this.carName = carName;
        this.carPrice = carPrice;
        this.releaseStore = releaseStore;
        this.charge = charge;
        this.progress = progress;
        this.cashBack = cashBack;
        this.releasePlace = releasePlace;
        this.supportContents = supportContents;
        this.etcContents = etcContents;
        this.enrollDate = enrollDate;
        this.carNumber = carNumber;
    }
}
