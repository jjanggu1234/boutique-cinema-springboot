package com.cinema.service;

import com.cinema.dto.qna.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {

  Long register(QuestionDTO questionDTO) throws Exception; // 등록

  QuestionDTO get(Long qNum) throws Exception; // 상세 조회

  void modify(QuestionDTO questionDTO) throws Exception; // 수정

  void remove(Long qNum) throws Exception; // 삭제

  Page<QuestionDTO> getfindAll(Pageable pageable) throws Exception; // 목록 조회 (페이징) (전체 목록 조회)
}
