package com.cinema.service;

import com.cinema.domain.Member;
import com.cinema.domain.MemberRole;
import com.cinema.dto.member.MemberJoinDTO;
import com.cinema.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class MemberServiceImpl  implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    // 저장 메서드
    @Transactional
    public void save(MemberJoinDTO dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = LocalDate.now().format(formatter); // 오늘 날짜 포맷팅
        Member member = Member.builder()
                .id(dto.getId())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .name(dto.getName())
                .birth(dto.getBirth())
                .phone(dto.getPhone())
                .years14More(dto.getYears14More())
                .useTermsAgree(dto.getUseTermsAgree())
                .personalInfoAgree(dto.getPersonalInfoAgree())
                .joinDate(formattedDate)
                .build();

        if ("admin".equals(dto.getId())) {
            member.addRole(MemberRole.ADMIN);
        } else {
            member.addRole(MemberRole.USER);
        }
        memberRepository.save(member); // Member 객체 저장
    }

    // 아이디 중복 체크 메서드
    public boolean findById(String id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.isEmpty(); // 존재하지 않으면 true, 존재하면 false
    }


    @Override
    public Page<Member>findAllMembers(Pageable pageable) {
        return memberRepository.findAll(pageable); //페이지 점보를 포함
    }
}
