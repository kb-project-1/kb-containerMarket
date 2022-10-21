package com.kb1.containerMarket.web.dto;

import com.kb1.containerMarket.web.controller.validation.ValidationGroups;
import com.kb1.containerMarket.web.domain.Member;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class JoinReqDto {

    @NotBlank(message = "이름은 비워 둘 수 없습니다", groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 1, max = 4, message = "이름은 3글자 까지만 입력가능합니다", groups = ValidationGroups.SizeGroup.class)
    @Pattern(regexp = "^[가-힇]*$", message = "한글만 입력 가능합니다", groups = ValidationGroups.PatternCheckGroup.class)
    private String name;

    @NotBlank(message = "아이디는 비워둘 수 없습니다.")
    @Size(min = 4, max = 16, message = "아이디는 4자 부터 16자까지 입력하여야 합니다.", groups = ValidationGroups.SizeGroup.class)
    @Pattern(regexp = "^[a-z0-9]*$",
            message = "아이디는 영어 소문자,숫자만 입력가능합니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String username;
    @NotBlank(message = "이메일은 비워 둘 수 없습니다", groups = ValidationGroups.NotBlankGroup.class)
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 비워 둘 수 없습니다",groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 8, max = 16, message = "비밀번호는 10자 부터 16자까지 입력하여야 합니다", groups = ValidationGroups.SizeGroup.class)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!~@#$%^&*_])[a-zA-Z\\d-~!@#$%^&*_]*$",
            message = "비밀번호는 특수기호, 영문, 숫자를 모두 포함해야합니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String password;
    @NotBlank(message = "휴대폰 번호는 비워둘 수 없습니다.")
    @Pattern(regexp = "^[0-9]*$",
            message = "휴대폰 번호는 숫자만 입력가능합니다.",
            groups = ValidationGroups.PatternCheckGroup.class)
    private String phone;


    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(new BCryptPasswordEncoder().encode(password))
                .name(name)
                .username(username)
                .phone(phone)
                .role_id(1)
                .build();
    }

}
