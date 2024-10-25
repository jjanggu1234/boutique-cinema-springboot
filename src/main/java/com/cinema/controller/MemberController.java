package com.cinema.controller;

import com.cinema.domain.Member;
import com.cinema.dto.member.FindIdDTO;
import com.cinema.dto.member.MemberJoinDTO;
import com.cinema.dto.member.CheckToResetPasswordDTO;
import com.cinema.dto.member.ResetPasswordDTO;
import com.cinema.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입
    @PostMapping("joinpage")
    public ResponseEntity<Member> join(@RequestBody MemberJoinDTO joinDTO) {
          memberService.save(joinDTO);
          return ResponseEntity.ok().build();
    }
    // 로그인
    @PostMapping("findId")
    public  ResponseEntity<String> findById(@RequestBody FindIdDTO findIdDTO) {
        String memberId = memberService.findId(findIdDTO);
        if (memberId != null) {
            return ResponseEntity.ok().body(memberId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // 비밀번호 수정을 위한 회원확인
    @PostMapping("check-member")
    public ResponseEntity<Void> checkToReset(@RequestBody CheckToResetPasswordDTO checkToResetPasswordDTO) {
        boolean exists = memberService.checkMemberToReset(checkToResetPasswordDTO);

        // 사용자가 존재하지 않으면 404 반환
        if (exists) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    // 비밀번호 재설정
    @PostMapping("reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        memberService.resetPassword(resetPasswordDTO);
        return ResponseEntity.ok().build();
    }
    // 아이디 중복체크
    @GetMapping("/check-id")
    public ResponseEntity<Boolean> checkId(String id) {
        boolean isAvailable = memberService.findById(id);
        return ResponseEntity.ok(isAvailable);
    }

  @GetMapping("/list")
public ResponseEntity<Page<Member>> getAllMembers(
       @RequestParam(defaultValue = "1") int page,
       @RequestParam(defaultValue = "10") int size,
       @RequestParam(required = false) String searchCondition)
       throws Exception {
    // 페이지 번호가 1보다 작은 경우 1로 설정
    if (page < 1) {
        page = 1;
    }
    Pageable pageable = PageRequest.of(page - 1, size);

    if (searchCondition == null) {
        // 검색 조건이 없을 경우 모든 멤버 조회
        Page<Member> members = memberService.findAllMembers(pageable);
        return ResponseEntity.ok(members);
    } else {
        // 검색 조건에 따른 멤버 조회
        Page<Member> members = memberService.findBySearchCondition(searchCondition, pageable);
        return ResponseEntity.ok(members);
    }
}

@GetMapping("/members")
public ResponseEntity<Page<Member>> getMembers(@RequestParam(required = false) String condition,
                                               Pageable pageable) throws Exception {
        Page<Member> members;
        if (condition != null && !condition.isEmpty()) {
            members = memberService.findBySearchCondition(condition, pageable);
        }else {
            members = memberService.findAllMembers(pageable);
        }
        return ResponseEntity.ok(members);
}

}
