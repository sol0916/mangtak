package com.delivery.springbootproject.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {

    private Integer user_no;     //유저번호
    private String user_type;     //유저식별
    private String user_id;         //유저아이디
    private String user_name;     //유저이름
    private String user_pw;         //유저비밀번호
    @Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}")
    private String user_phone;     //유저폰번호
    private String user_area;     //유저담당지역
    @Email
    private String user_email;   //유저이메일
    private String user_jobassign; //유저업무
}

