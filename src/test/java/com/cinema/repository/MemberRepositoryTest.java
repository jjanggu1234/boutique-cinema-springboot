//package com.cinema.repository;
//
//import com.cinema.domain.Member;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//
//@SpringBootTest
//public class MemberRepositoryTest {
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    public void testInsertMember() {
//        // 포맷을 설정합니다.
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
//        String formattedDate = LocalDate.now().format(formatter); // 오늘 날짜 포맷팅
//
//        Member member = Member.builder()
//                .id("qscft9898")
//                .password(passwordEncoder.encode("qscft9898"))
//                .email("qscft9898@gmail.com")
//                .name("기승환")
//                .birth("19960629")
//                .phone("01029734085")
//                .years_14_more(1) // 14세 이상 동의 값 설정
//                .use_terms_agree(1) // 이용약관 동의 값 설정
//                .personal_info_agree(1) // 개인정보 동의 값 설정
//                .joinDate(formattedDate) // 포맷된 문자열 날짜로 설정
//                .build();
//
//        memberRepository.save(member); // 회원 저장
//    }
//
//}


