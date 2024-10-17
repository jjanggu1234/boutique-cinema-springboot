package com.cinema.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@SequenceGenerator(
    name = "Q_NUM_GEN", // 시퀀스 제너레이터 이름
    sequenceName = "Q_NUM_SEQ", // 시퀀스 이름
    initialValue = 1, // 시작값
    allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
    )
@Table(name = "QUESTION_TBL")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Q_Num_Gen")
  private Long qNum; // 질문 문의글 번호

  @ManyToOne
  @JoinColumn(name = "M_ID", nullable = false)
  private Member member; // 회원 아이디

  @Column(nullable = false, length = 50)
  private String qTitle; // 질문 제목

  @Column(nullable = false, length = 3000)
  private String qContent; // 질문 내용

  @Column(nullable = false, length = 10)
  private String qStatus; // 질문 답변상태

  @Column(nullable = false)
  private LocalDate qDate; // 질문 작성일
}
