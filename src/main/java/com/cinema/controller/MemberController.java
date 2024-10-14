package com.cinema.controller;

import com.cinema.domain.Member;
//import com.cinema.dto.member.MemberDTO;
import com.cinema.dto.member.AdminMemberListDTO;
import com.cinema.dto.member.MemberJoinDTO;
import com.cinema.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberController {

    private final MemberService memberService;

//    @PostMapping("/joinPage")
//    public void register(@RequestBody MemberJoinDTO dto) {
//        memberService.save(dto);   // 회원 가입 메서드 호출
//    }

    @PostMapping("/joinpage")
    public ResponseEntity<Member> join(@RequestBody MemberJoinDTO joinDTO) {
          memberService.save(joinDTO);
          return ResponseEntity.ok().build();
    }

    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkId(String id) {
        boolean isAvailable = memberService.findById(id);
        return ResponseEntity.ok(isAvailable);
    }

    // 전체 회원 조회 API (관리자만 접근 가능)
    @GetMapping("/admin/userlist")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdminMemberListDTO>> getAllMembers() {
        List<AdminMemberListDTO> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    // 회원 검색 API (관리자만 접근 가능)
    @GetMapping("/admin/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdminMemberListDTO>> searchMember(@RequestParam("keyword") String keyword) {
        List<AdminMemberListDTO> members = memberService.searchMembers(keyword);
        return ResponseEntity.ok(members);
    }

}
