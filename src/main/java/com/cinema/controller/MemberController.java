package com.cinema.controller;

import com.cinema.domain.Member;
import com.cinema.dto.member.MemberJoinDTO;
import com.cinema.service.MemberService;
import com.cinema.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("joinpage")
    public ResponseEntity<Member> join(@RequestBody MemberJoinDTO joinDTO) {
          memberService.save(joinDTO);
          return ResponseEntity.ok().build();
    }

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
