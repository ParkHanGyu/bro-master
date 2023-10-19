package com.brogrammers.brogrammers.web.memberController;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {
    // @NotEmpty 는 null 과 "" 둘 다 허용하지 않게 합니다.
    @NotEmpty(message = "이메일 기입은 필수 입니다.")
    private String memberEmail;

    // @NotBlank 는 null 과 "" 과 " " 모두 허용하지 않습니다.
    @NotBlank
    private String password;
}
