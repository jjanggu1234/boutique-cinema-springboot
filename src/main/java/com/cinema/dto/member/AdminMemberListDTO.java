package com.cinema.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminMemberListDTO {
    @Pattern(regexp = "^[a-zA-Z0-9]{8,10}$", message = "아이디는 영문/숫자 포함 8~10자이어야 합니다.") // 수정된 정규 표현식
    private String id;

    @Pattern(regexp = ".{1,10}", message = "이름은 10자 이내여야 합니다.")
    private String name;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "생년월일 형식이 올바르지 않습니다. (ex) 0000-00-00") // 수정된 정규 표현식
    private String birth;

    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "휴대폰번호 형식이 올바르지 않습니다. (ex) 010-0000-0000")
    private String phone;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "가입일 형식이 올바르지 않습니다. (ex) 0000-00-00")
    private String joinDate;

    private Integer isDeleted = 0;  // 기본 값은 0으로 설정 (0 = 회원, 1 = 탈퇴)
    private Integer isTreated;      // 기본 값은 0으로 설정 (0 = 일반, 1 = 우대)
}
