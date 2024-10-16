package com.cinema.repository;

import com.cinema.domain.Movie;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {
  // 검색어 포함 쿼리문 작성
  // @Param 어노테이션을 사용하여 메서드 파라미터와 쿼리 파라미터를 연결
  @Query("SELECT m FROM Movie m WHERE m.korTitle LIKE :korTitle") // LIKE절에 :korTitle은 입력된 검색어를 대체함
  Page<Movie> findByKorTitle(@Param("korTitle") String korTitle, Pageable pageable); // 페이징 기능 지원

  @Query("SELECT m FROM Movie m ORDER BY m.regDate DESC") // 개봉일 기준으로 최신순으로 정렬하는 쿼리
  Page<Movie> findLatestByDate(Pageable pageable); // 페이징 기능을 지원함

  @Query("SELECT m FROM Movie m ORDER BY m.regDate") // 개봉일 기준으로 오래된순으로 정렬하는 쿼리
  Page<Movie> findEarliestByDate(Pageable pageable); // 페이징 기능 지원

  // 개봉일과 상영종료일 사이의 영화를 찾는 쿼리 메서드
  @Query(
      "SELECT m FROM Movie m WHERE m.movieStartDate <= :targetDate AND m.movieEndDate >= :targetDate")
  List<Movie> findMoviesByDate(@Param("targetDate") LocalDate targetDate);
}
