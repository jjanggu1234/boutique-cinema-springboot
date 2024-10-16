package com.cinema.service;

import com.cinema.dto.movie.MovieDTO;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
  Long register(MovieDTO movieDTO) throws Exception; // 등록

  MovieDTO get(Long movieNum) throws Exception; // 상세 조회

  void modify(MovieDTO movieDTO) throws Exception; // 수정

  void remove(Long movieNum) throws Exception; // 삭제

  Page<MovieDTO> getfindAll(Pageable pageable) throws Exception; // 목록 조회 (페이징) (전체 목록 조회)

  Page<MovieDTO> findByKorTitle(String korTitle, Pageable pageable)
      throws Exception; // 목록 조회 (제목 검색)

  Page<MovieDTO> getMoviesLatestByDate(Pageable pageable) throws Exception; // 최신순으로 정렬된 목록 조회

  Page<MovieDTO> getMoviesEarliestByDate(Pageable pageable) throws Exception; // 오래된순으로 정렬된 목록 조회

  List<MovieDTO> getMoviesByDate(LocalDate targetDate) throws Exception;
}
