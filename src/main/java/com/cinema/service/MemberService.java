package com.cinema.service;

import com.cinema.domain.Member;
import com.cinema.dto.member.AdminMemberListDTO;
import com.cinema.dto.member.MemberJoinDTO;
import com.cinema.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MemberService {

    // 회원 가입 메서드
    void save(MemberJoinDTO dto);
    // 아이디 중복체크 메서드
    boolean findById(String id);
    // 전체 회원 목록 조회 메서드
    List<AdminMemberListDTO> getAllMembers();
    // 특정 조건에 맞는 회원 검색 메서드
    List<AdminMemberListDTO> searchMembers(String keyword);
}
