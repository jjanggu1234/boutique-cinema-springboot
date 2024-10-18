package com.cinema.service;

import com.cinema.domain.Member;
import com.cinema.dto.member.MemberJoinDTO;
import com.cinema.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface MemberService {

    // 회원 가입 메서드
    void save(MemberJoinDTO dto);
    // 아이디 중복체크 메서드
    boolean findById(String id);

    Page<Member> findAllMembers(Pageable pageable);
}
