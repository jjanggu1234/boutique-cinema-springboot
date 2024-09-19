package com.cinema.domain;

import java.util.Date;

public class Movie {

    private int movieNum;           // 영화번호
    private String korTitle;        // 한글제목
    private String EnTitle;         // 영문제목
    private String movieDesc;       // 영화소개
    private int runTime;            // 상영시간
    private String genre;           // 장르
    private String posterUrl;       // 포스터 URL
    private String trailerUrl;      // 예고편 URL
    private String director;        // 감독
    private String cast;            // 출연진
    private String rating;          // 관람등급
    private Date movieStratDate;    // 개봉일
    private Date movieEndDate;      // 상영종료일
    private int theaterNum;         // 상영관번호
    private int _1round;            // 1회차 상영
    private int _2round;            // 2회차 상영
    private int _3round;            // 3회차 상영
    private int _4round;            // 4회차 상영
    private int _5round;            // 5회차 상영
    private String _1roundTime;     // 1회차 상영시간
    private String _2roundTime;     // 2회차 상영시간
    private String _3roundTime;     // 3회차 상영시간
    private String _4roundTime;     // 4회차 상영시간
    private String _5roundTime;     // 5회차 상영시간
    private Date regDate;           // 영화등록일
}
