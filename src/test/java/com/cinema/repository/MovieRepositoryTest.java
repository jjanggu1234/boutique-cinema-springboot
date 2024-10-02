package com.cinema.repository;

import com.cinema.domain.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test                        //데이터 입력 테스트
    public void insertTest(){
        for (int i = 0; i < 5; i++) {
            Movie movie = Movie.builder()
                    .korTitle("영화제목"+i)
                    .enTitle("영어제목"+i)
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
                    .theaterNum(0)
                    .roundTime1("1회차"+i)
                    .roundTime2("2회차"+i)
                    .roundTime3("3회차"+i)
                    .roundTime4("4회차"+i)
                    .roundTime5("5회차"+i)
                    .regDate(LocalDate.now())
                    .build();

            movieRepository.save(movie);
        }
    }
//
//    @Test                   //데이터 조회 테스트
//    public void ReadTest() {
//        Long movieNum = 3L;
//        java.util.Optional<Movie> result = movieRepository.findById(movieNum);
//        Movie movie = result.orElseThrow();
//    }
//
//    @Test                      //데이터 수정 테스트
//    public void ModifyTest() {
//        Long movieNum = 3L;
//        java.util.Optional<Movie> result = movieRepository.findById(movieNum);
//        Movie movie = result.orElseThrow();
//        //수정할 내용 작성하여 테스트
//        movieRepository.save(movie);
//    }
//
//    @Test                     //데이터 삭제 테스트
//    public void DeleteTest() {
//        Long movieNum = 1L;
//
//        movieRepository.deleteById(movieNum);
//    }
//
//    @Test                      //페이징 테스트
//    public void PageingTest() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("movieNum").descending());
//        Page<Movie> movies = movieRepository.findAll(pageable);
//        movies.getContent().stream().forEach(System.out::println);
//    }

}