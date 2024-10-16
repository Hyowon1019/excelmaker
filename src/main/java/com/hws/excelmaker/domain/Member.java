package com.hws.excelmaker.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class Member {

    private Long id;

    @NotEmpty(message = "* 필수항목")
    private String loginId;

    @NotEmpty(message = "* 필수항목")
    private String password;

    @NotEmpty(message = "* 필수항목")
    private String name;

    @NotEmpty(message = "* 필수항목")
    private String belong;

    @Embedded
    private Address address;


}
