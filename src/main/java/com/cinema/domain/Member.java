package com.cinema.domain;

import com.cinema.dto.member.MemberJoinDTO;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Table(name = "MEMBER_TBL")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString(exclude = "memberRoleList")
public class Member  {

    @Id
    @Column(name = "M_ID", length = 20)
    private String id;             // 회원 아이디  , 기본키

    @Column(name = "M_PASSWORD", nullable = false, length = 200)
    private String password;       // 회원 비밀번호

    @Column(name = "M_EMAIL", nullable = false, length = 40)
    private String email;          // 회원 이메일

    @Column(name = "M_NAME", nullable = false, length = 20)
    private String name;           // 회원 성명

    @Column(name = "M_BIRTHDAY", nullable = false, length = 8)
    private String birth;       // 회원 생년월일

    @Column(name = "M_PHONENUM", nullable = false, length = 11)
    private String phone;       // 회원 휴대폰번호

    @Column(name = "YEARS_14_MORE_AGREE", nullable = false)
    private Integer years14More;   // 14세 이상 동의

    @Column(name = "USE_TERMS_AGREE", nullable = false)
    private Integer useTermsAgree;      // 이용약관 동의

    @Column(name = "PERSONAL_INFO_AGREE", nullable = false)
    private Integer personalInfoAgree;  // 개인정보 동의

    @Column(name = "JOIN_DATE")
    private String joinDate;          // 회원 가입일

    @Column(name = "ISTREATED", columnDefinition = "int default 0")
    @Builder.Default
    private Integer isTreated = 0;    // 우대여부

    @Column(name = "ISDELETED", columnDefinition = "int default 0")
    @Builder.Default
    private Integer isDeleted = 0;     // 탈퇴여부

    @ElementCollection(fetch = FetchType.LAZY)      // 사용자 , 관리자 식별
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();

    public void addRole(MemberRole memberRole) {
        memberRoleList.add(memberRole);
    }
    public void clearRole() {
        memberRoleList.clear();
    }

    // 회원 가입 시 joinDate를 현재 시간으로 설정하는 메서드
    public static Member createMember(MemberJoinDTO dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = LocalDate.now().format(formatter);   // 오늘 날짜 포맷팅
        return Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .name(dto.getName())
                .birth(dto.getBirth())
                .phone(dto.getPhone())
                .years14More(dto.getYears14More())
                .useTermsAgree(dto.getUseTermsAgree())
                .personalInfoAgree(dto.getPersonalInfoAgree())
                .joinDate(formattedDate) // 현재 시간으로 설정
                .build();
    }

}
