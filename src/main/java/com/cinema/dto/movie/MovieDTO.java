package com.cinema.dto.movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private Long movieNum;                // 영화번호
    private String korTitle;             // 한글제목
    private String enTitle;              // 영문제목
    private String movieDesc;            // 영화소개
    private int runTime;                 // 상영시간
    private String genre;                // 장르
    private String posterUrl;            // 포스터 URL
    private String trailerUrl;           // 예고편 URL
    private String director;             // 감독
    private String cast;                 // 출연진
    private String rating;               // 관람등급

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDate movieStartDate;         // 개봉일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDate movieEndDate;           // 상영종료일
    private Integer theaterNum;          // 상영관번호
    private Integer round1;            // 1회차 상영  //Integer를 사용한 이유 => int와 같은 기본 타입은 null 값을 가질 수 없기 때문에 자동으로 Notnull 제약조건이 생김
    private Integer round2;            // 2회차 상영
    private Integer round3;            // 3회차 상영
    private Integer round4;            // 4회차 상영
    private Integer round5;            // 5회차 상영
    private String roundTime1;         // 1회차 상영시간
    private String roundTime2;         // 2회차 상영시간
    private String roundTime3;         // 3회차 상영시간
    private String roundTime4;         // 4회차 상영시간
    private String roundTime5;         // 5회차 상영시간

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private LocalDate regDate;               // 영화등록일

    //MultipartFile의 리스트인 files는 새상품의 등록,수정 작업시 사용자가 새파일 업로드 할때 사용
    //files는 새롭게 서버에 보내지는 실제 파일 데이터
    @Builder.Default
    private String files = "";

    //uploadFileNames는 업로드가 완료된 파일의 이름만 문자열로 보관한 리스트
    //문자열로 업로드된 결과만 갖고있기때문에 이걸 이용해 DB에 파일 이름을 처리하는 용도로 사용함
    @Builder.Default
    private String uploadFileNames = "";
}
