package com.delivery.springbootproject.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MypageVO {

    private int user_no;
    private String user_type;
    private String user_id;
    private String user_name;
    @Min(value=8, message = "비밀번호는 8글자 이상 입력해주세요.")
    private String user_pw;
    @Pattern(message = "000-0000-0000 형식이이어합니다.", regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}")
    private String user_phone;
    private String user_area;
    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Email(message = "이메일 형식이어야 합니다.") // 이메일은 공백이 통과
    private String user_email;
    private String user_jobassign;



}
