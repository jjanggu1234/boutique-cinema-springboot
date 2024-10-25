package com.cinema.controller;

import com.cinema.dto.qna.QuestionDTO;
import com.cinema.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

  private final QuestionService questionService;

  // 질문 등록
  @PostMapping
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<Long> register(QuestionDTO questionDTO) throws Exception {
    Long qNum = questionService.register(questionDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(qNum);
  }

  // 질문 상세 조회
  @GetMapping("/{qNum}")
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
  public QuestionDTO getQuestion(@PathVariable Long qNum) throws Exception {
    return questionService.get(qNum);
  }

  // 질문 수정
  @PutMapping("/{qNum}")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<Void> modify(
      @PathVariable Long qNum, @ModelAttribute QuestionDTO questionDTO) throws Exception {

    // 질문 수정
    questionDTO.setQNum(qNum); // DTO에 ID 세팅
    questionService.modify(questionDTO);
    return ResponseEntity.noContent().build();
  }

  // 질문 삭제
  @DeleteMapping("/{qNum}")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<Void> remove(@PathVariable Long qNum) throws Exception {
    questionService.remove(qNum);
    return ResponseEntity.noContent().build();
  }

  // 질문 목록 리스트 (페이징)
  @GetMapping("/list")
  @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
  public Page<QuestionDTO> getQuestion(
      @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size)
      throws Exception {

    return questionService.getfindAll(PageRequest.of(page - 1, size));
  }
}
