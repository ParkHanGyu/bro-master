package com.brogrammers.brogrammers.web.memberController;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberForm {

    // @NotEmpty 는 null 과 "" 둘 다 허용하지 않게 합니다.
    @NotEmpty(message = "이메일 기입은 필수 입니다.")
    private String email;
    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;
    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    private String pwd;
    @NotEmpty(message = "비밀번호 확인 입력란은 필수 입니다..")
    private String pwdChk;
    private String postal_code;
    private String middle_address;
    private String detailed_address;


}
