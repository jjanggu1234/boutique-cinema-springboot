package com.cinema.service;

import com.cinema.domain.Member;
import com.cinema.dto.member.MemberJoinDTO;
import com.cinema.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class MemberServiceImpl  implements MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 저장 메서드
    public void save(MemberJoinDTO dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        String formattedDate = LocalDate.now().format(formatter); // 오늘 날짜 포맷팅
        Member member = Member.builder()
                .id(dto.getId())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .name(dto.getName())
                .birth(dto.getBirth())
                .phone(dto.getPhone())
                .years_14_more(dto.getYears_14_more())
                .use_terms_agree(dto.getUse_terms_agree())
                .personal_info_agree(dto.getPersonal_info_agree())
                .joinDate(formattedDate)
                .build();

        memberRepository.save(member); // Member 객체 저장

    }

    // 아이디 중복 체크 메서드
    public boolean findById(String id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.isEmpty(); // 존재하지 않으면 true, 존재하면 false
    }



}
