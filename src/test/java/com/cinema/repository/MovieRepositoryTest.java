package com.cinema.repository;

import com.cinema.domain.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test                        //데이터 입력 테스트
    public void insertTest(){
        for (int i = 0; i < 5; i++) {
            Movie movie = Movie.builder()
                    .movieNum(i)
                    .korTitle("영화제목"+i)
                    .EnTitle("영어제목"+i)
                    .movieDesc("영화제목"+i)
                    .runTime(90)
                    .genre("호러"+i)
                    .posterUrl("포스터url"+i)
                    .trailerUrl("예고편URL"+i)
                    .director("감독"+i)
                    .cast("출연진"+i)
                    .rating("15세"+i)
                    .movieStartDate(LocalDate.of(2024,9,20))
                    .movieEndDate(LocalDate.of(2024,9,20))
                    .theaterNum(5)
                    ._1roundTime("1회차"+i)
                    ._2roundTime("2회차"+i)
                    ._3roundTime("3회차"+i)
                    ._4roundTime("4회차"+i)
                    ._5roundTime("5회차"+i)
                    .regDate(LocalDate.now())
                    .build();

            movieRepository.save(movie);
        }
    }

//    @Test                   //데이터 조회 테스트
//    public void ReadTest() {
//        Integer movieNum = 3;
//        java.util.Optional<Movie> movies = movieRepository.findById(movieNum);
//    }
}