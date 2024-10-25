package com.cinema.service;

import com.cinema.domain.Member;
import com.cinema.dto.member.FindIdDTO;
import com.cinema.dto.member.MemberJoinDTO;
import com.cinema.dto.member.CheckToResetPasswordDTO;
import com.cinema.dto.member.ResetPasswordDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface MemberService {

    // 회원 가입 메서드
    void save(MemberJoinDTO dto);
    // 아이디 중복체크 메서드
    boolean findById(String id);
    // 아이디 찾기 메서드
    String findId(FindIdDTO findIdDTO);
    // 비밀번호 재설정을 위해 회원값 확인 메서드
    boolean checkMemberToReset(CheckToResetPasswordDTO checkToResetPasswordDTO);
    // 비밀번호 업데이트 메서드
    void resetPassword(ResetPasswordDTO resetPasswordDTO);

    Page<Member> findAllMembers(Pageable pageable) throws Exception;

    Page<Member> findBySearchCondition(String condition, Pageable pageable) throws Exception;



}
