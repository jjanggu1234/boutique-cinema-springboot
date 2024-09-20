package com.cinema.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.util.Date;

@Entity                             //해당 클래스가 JPA의 엔티티임을 나타냄
@Table(name ="MOVIE_TBL")           //테이블명을 지정함
@Builder
@Getter
@ToString
@AllArgsConstructor                 //모든 필드를 인자로 받는 생성자를 자동으로 생성
@NoArgsConstructor                  //기본 생성자를 자동으로 생성
public class Movie {

    @Id                             //해당 필드가 엔티티의 기본 키임을 나타내는 에너테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //기본 키의 값을 자동으로 생성
    private int movieNum;                // 영화번호

    @Column(nullable = false)            // NotNull 적용하기 위한 에너테이션
    private String korTitle;             // 한글제목
    @Column(nullable = false)
    private String EnTitle;              // 영문제목
    @Column(nullable = false)
    private String movieDesc;            // 영화소개

    private int runTime;                 // 상영시간

    @Column(nullable = false)
    private String genre;                // 장르
    @Column(nullable = false)
    private String posterUrl;            // 포스터 URL
    @Column(nullable = false)
    private String trailerUrl;           // 예고편 URL
    @Column(nullable = false)
    private String director;             // 감독
    @Column(nullable = false)
    private String cast;                 // 출연진
    @Column(nullable = false)
    private String rating;               // 관람등급
    @Column(nullable = false)
    private Date movieStratDate;         // 개봉일
    @Column(nullable = false)
    private Date movieEndDate;           // 상영종료일
    @Column(nullable = false)
    private Integer theaterNum;         // 상영관번호

    private Integer _1round;            // 1회차 상영  //Integer를 사용한 이유 => int와 같은 기본 타입은 null 값을 가질 수 없기 때문에 자동으로 Notnull 제약조건이 생김
    private Integer _2round;            // 2회차 상영
    private Integer _3round;            // 3회차 상영
    private Integer _4round;            // 4회차 상영
    private Integer _5round;            // 5회차 상영

    @Column(nullable = false)
    private String _1roundTime;         // 1회차 상영시간
    @Column(nullable = false)
    private String _2roundTime;         // 2회차 상영시간
    @Column(nullable = false)
    private String _3roundTime;         // 3회차 상영시간
    @Column(nullable = false)
    private String _4roundTime;         // 4회차 상영시간
    @Column(nullable = false)
    private String _5roundTime;         // 5회차 상영시간
    @Column(nullable = false)
    private Date regDate;               // 영화등록일
}
