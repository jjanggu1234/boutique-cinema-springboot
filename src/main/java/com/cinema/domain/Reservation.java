package com.cinema.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "movie_reservation_tbl")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
  @Id
  @Size(min = 12)
  private String rNum; // 영화예매번호

  @Column(nullable = false)
  private Integer theaterNum; // 영화예매 상영관 번호

  @Column(nullable = false)
  private Integer roundNum; // 영화예매 상영회차 번호

  @Column(nullable = false)
  private int paymentAmount; // 영화예매 결제금액

  @Column(nullable = false)
  private LocalDate reserveDate; // 영화예매날짜

  @CreatedDate
  @Column(updatable = false, insertable = true)
  private LocalDateTime regDate; // 예매 등록일

  @Column(length = 1)
  private Integer isCanceled; // 영화예매 취소여부

  private LocalDateTime cancelDate; // 영화예매 취소일자

  @Column(length = 100)
  private String reviewContent; // 관람후기내용

  @Column(precision = 2, scale = 0)
  private Integer reviewRating; // 관람후기평점

  @Column(length = 3, nullable = false)
  private String rPersonType1; // 예매인원1 구분

  @Column(length = 3)
  private String rPersonType2; // 예매인원2 구분

  @Column(length = 3)
  private String rPersonType3; // 예매인원3 구분

  @Column(length = 3)
  private String rPersonType4; // 예매인원4 구분

  @Column(length = 3)
  private String rPersonType5; // 예매인원5 구분

  @Column(length = 3)
  private String rPersonType6; // 예매인원6 구분

  @Column(nullable = false)
  private int rPersonPrice1; // 예매인원1 가격

  private int rPersonPrice2; // 예매인원2 가격
  private int rPersonPrice3; // 예매인원3 가격
  private int rPersonPrice4; // 예매인원4 가격
  private int rPersonPrice5; // 예매인원5 가격
  private int rPersonPrice6; // 예매인원6 가격

  @Column(length = 3, nullable = false)
  private String seatNum1; // 좌석번호1

  @Column(length = 3)
  private String seatNum2; // 좌석번호2

  @Column(length = 3)
  private String seatNum3; // 좌석번호3

  @Column(length = 3)
  private String seatNum4; // 좌석번호4

  @Column(length = 3)
  private String seatNum5; // 좌석번호5

  @Column(length = 3)
  private String seatNum6; // 좌석번호6

  @ManyToOne
  @JoinColumn(name = "m_id", nullable = false) // 회원 아이디 fk
  private Member member;

  @ManyToOne
  @JoinColumn(name = "movie_num", nullable = false) // 영화번호 fk
  private Movie movie;
}
