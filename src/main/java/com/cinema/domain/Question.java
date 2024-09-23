package com.cinema.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@SequenceGenerator(name = "Q_Num_Gen",      // 시퀀스 제너레이터 이름
            sequenceName = "Q_Num_Seq",     // 시퀀스 이름
            initialValue = 1,       // 시작값
            allocationSize = 1      // 메모리를 통해 할당할 범위 사이즈
)
@Table(name = "QUESTION_TABLE")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Q_Num_Gen")
    private int qNum;           // 질문 문의글 번호
    private String M_ID;        // 회원 아이디
    private String qTitle;      // 질문 제목
    private String qContent;    // 질문 내용
    private Date qDate;         // 질문 작성일
    private String qStatus;     // 질문 답변상태
}
