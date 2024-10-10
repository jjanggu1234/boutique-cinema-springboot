package com.cinema.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private int aNum;           // 문의 답변 번호
//    private String aTitle;      // 문의 답변 제목
//    private String aContent;    // 문의 답변 내용
//    private Date aDate;         // 문의 답변일
}
