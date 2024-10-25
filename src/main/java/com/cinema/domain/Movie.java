package com.cinema.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity                             //해당 클래스가 JPA의 엔티티임을 나타냄
@Table(name ="MOVIE_TBL")           //테이블명을 지정함
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor                 //모든 필드를 인자로 받는 생성자를 자동으로 생성
@NoArgsConstructor
@SequenceGenerator(name = "MOVIE_SEQ_GEN", //시퀀스 제네레이터 이름
        sequenceName = "MOVIE_SEQ", //시퀀스 이름
        initialValue = 1, //시작값
        allocationSize = 1 //메모리를 통해 할당할 범위 사이즈
)
public class Movie {

    @Id                             //해당 필드가 엔티티의 기본 키임을 나타내는 에너테이션
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIE_SEQ_GEN")  //사용할 전략을 시퀀스로 선택, 식별자 생성기를 설정해 놓은 TODO_SEQ_GEN으로 설정
    private Long movieNum;                // 영화번호

    @Column(nullable = false, length = 30)            // NotNull 적용하기 위한 에너테이션
    private String korTitle;             // 한글제목
    @Column(nullable = false, length = 50)
    private String enTitle;              // 영문제목
    @Column(nullable = false, length = 4000)
    private String movieDesc;            // 영화소개

    private int runTime;                 // 상영시간

    @Column(nullable = false, length = 20)
    private String genre;                // 장르
    @Column(nullable = false, length = 200)
    private String posterUrl;            // 포스터 URL
    @Column(nullable = false, length = 1000)
    private String trailerUrl;           // 예고편 URL
    @Column(nullable = false, length = 30)
    private String director;             // 감독
    @Column(nullable = false, length = 50)
    private String cast;                 // 출연진
    @Column(nullable = false, length = 10)
    private String rating;               // 관람등급
    @Column(nullable = false)
    private LocalDate movieStartDate;         // 개봉일
    @Column(nullable = false)
    private LocalDate movieEndDate;           // 상영종료일
    @Column(nullable = false)
    private Integer theaterNum;          // 상영관번호
    @Column(length = 1)
    private Integer round1;            // 1회차 상영  //Integer를 사용한 이유 => int와 같은 기본 타입은 null 값을 가질 수 없기 때문에 자동으로 Notnull 제약조건이 생김
    @Column(length = 1)
    private Integer round2;            // 2회차 상영
    @Column(length = 1)
    private Integer round3;            // 3회차 상영
    @Column(length = 1)
    private Integer round4;            // 4회차 상영
    @Column(length = 1)
    private Integer round5;            // 5회차 상영

    @Column(nullable = false, length = 10)
    private String roundTime1;         // 1회차 상영시간
    @Column(nullable = false,length = 10)
    private String roundTime2;         // 2회차 상영시간
    @Column(nullable = false,length = 10)
    private String roundTime3;         // 3회차 상영시간
    @Column(nullable = false,length = 10)
    private String roundTime4;         // 4회차 상영시간
    @Column(nullable = false,length = 10)
    private String roundTime5;         // 5회차 상영시간
    @Column(nullable = false)
    private LocalDate regDate;               // 영화등록일

}
