package com.cinema.service;

import com.cinema.dto.movie.MovieDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test                      //데이터 등록
    public void registerTest() throws Exception {
        MovieDTO moiveDTO = MovieDTO.builder()
                .korTitle("영화제목")
                .enTitle("영어제목")
                .movieDesc("영화제목")
                .runTime(90)
                .genre("호러")
                .posterUrl("포스터url")
                .trailerUrl("예고편URL")
                .director("감독")
                .cast("출연진")
                .rating("15세")
                .movieStartDate(LocalDate.of(2024,9,20))
                .movieEndDate(LocalDate.of(2024,9,20))
                .theaterNum(5)
                .roundTime1("1회차")
                .roundTime2("2회차")
                .roundTime3("3회차")
                .roundTime4("4회차")
                .roundTime5("5회차")
                .regDate(LocalDate.now())
                .build();

        Long movienum = movieService.register(moiveDTO);

    }

    @Test                 //데이터 조회
    public void ReadTest() throws Exception {
        Long movieNum = 14L;
        MovieDTO movieDTO = movieService.get(movieNum);
    }
}