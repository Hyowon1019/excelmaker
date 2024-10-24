package com.hws.excelmaker.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data // @Data 에 있는 @RequiredArgsConstructor는 final 키워드나 @NotNull 이 붙은 필드를 포함한 생성자를 자동으로 만들어 준다.
// 따라서 해당 클래스의 필드 중 @NotNull 이나 final 키워드가 붙지 않은 필드가 존재하므로 직접 생성자 코드를 작성하거나,
// @AllArgsConstructor 애노테이션을 이용하면 된다. 나는 직접 생성자를 작성하였다.
public class ExcelData {

    @NotNull
    private String contractDate;

    @NotBlank
    private String customerName;

    @NotBlank
    private String belong;

    @NotBlank
    private String carName;

    @NotNull
    @Max(500000000)
    private Integer carPrice;

    @NotBlank
    private String releaseStore;

    @NotNull
    private Double charge;

    @NotNull
    private String progress;

    @NotNull
    private Integer cashBack;

    @NotBlank
    private String releasePlace;

    private String supportContents;
    private String etcContents;

    @NotBlank
    private String enrollDate;

    @NotBlank
    private String carNumber;

    public ExcelData() {}

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
