package com.cinema.repository;

import com.cinema.domain.Member;

import com.cinema.domain.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testInsertMember() {
        // 포맷을 설정합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = LocalDate.now().format(formatter); // 오늘 날짜 포맷팅

        Member member = Member.builder()
                .id("qscft9898")
                .password(passwordEncoder.encode("qscft9898"))
                .email("qscft9898@gmail.com")
                .name("기승환")
                .birth("19960629")
                .phone("01029734085")
                .years14More(1) // 14세 이상 동의 값 설정
                .useTermsAgree(1) // 이용약관 동의 값 설정
                .personalInfoAgree(1) // 개인정보 동의 값 설정
                .joinDate(formattedDate) // 포맷된 문자열 날짜로 설정
                .build();

                member.addRole(MemberRole.USER);

//        Member member = Member.builder()                                  // 관리자 계정 임의추가
//                .id("admin")
//                .password(passwordEncoder.encode("1234"))
//                .email("qscft9898@gmail.com")
//                .name("어드민")
//                .birth("20000101")
//                .phone("01001010101")
//                .years14More(1) // 14세 이상 동의 값 설정
//                .useTermsAgree(1) // 이용약관 동의 값 설정
//                .personalInfoAgree(1) // 개인정보 동의 값 설정
//                .joinDate(formattedDate) // 포맷된 문자열 날짜로 설정
//                .build();
//
//                member.addRole(MemberRole.ADMIN);

        memberRepository.save(member); // 회원 저장
    }

}


