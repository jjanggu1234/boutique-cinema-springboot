package com.cinema.service;

import com.cinema.dto.movie.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService {
    Long register (MovieDTO movieDTO);    // 등록
    MovieDTO get(Long movieNum);          // 상세 조회
    void modify(MovieDTO movieDTO);       // 수정
    void remove(Long movieNum);           // 삭제
    Page<MovieDTO> getfindAll(Pageable pageable); // 목록 조회 (페이징) (전체 목록 조회)
    Page<MovieDTO> findByKorTitle(String korTitle, Pageable pageable);  // 목록 조회 (제목 검색)
}
