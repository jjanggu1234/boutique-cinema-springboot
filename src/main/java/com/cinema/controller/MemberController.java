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

@PatchMapping("/{id}/treated")
public ResponseEntity<Void> updateTreatedStatus(@PathVariable String id, @RequestBody Integer isTreated) {
    // 입력 검증
    if (isTreated == null || (isTreated != 0 && isTreated != 1)) {
        return ResponseEntity.badRequest().build(); // 잘못된 요청
    }

    // 서비스에서 우대 여부 업데이트 메소드 호출
    try {
        memberService.updateTreatedStatus(id, isTreated);
        return ResponseEntity.ok().build(); // 성공적으로 업데이트
    } catch (NoSuchElementException e) {
        return ResponseEntity.notFound().build(); // 사용자가 존재하지 않는 경우
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 기타 오류 처리
    }
}
}
