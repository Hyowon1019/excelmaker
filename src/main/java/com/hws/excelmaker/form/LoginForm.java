package com.hws.excelmaker.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty(message = "아이디를 입력하시오.")
    private String loginId;

    @NotEmpty(message = "패스워드를 입력하시오.")
    private String password;
}
