package com.cinema.repository;

import com.cinema.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // 검색어 포함 쿼리문 작성
    //@Param 어노테이션을 사용하여 메서드 파라미터와 쿼리 파라미터를 연결
    @Query("SELECT m FROM Movie m WHERE m.korTitle LIKE :korTitle")  // LIKE절에 :korTitle은 입력된 검색어를 대체함
    Page<Movie> findByKorTitle(@Param("korTitle") String korTitle, Pageable pageable);
}
