package com.cinema.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "movie_reservation_tbl")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    private String rNum;            // 영화예매번호

    @Column(nullable = false, length = 3)
    private String seatNum;         // 영화예매 좌석번호

    @Column(nullable = false)
    private int paymentAmount;      // 영화예매 결제금액

    @Column(nullable = false)
    private Date rDate;             // 영화예매일자

    @Column(length = 1)
    private int isCanceled;         // 영화예매 취소여부

    private Date cancelDate;        // 영화예매 취소일자

    @Column(length = 500)
    private String reviewContent;   // 관람후기내용

    @Column(precision = 3, scale = 1)
    private BigDecimal reviewRating;       // 관람후기평점

    @Column(length = 3, nullable = false)
    private String rPersonType1;    // 예매인원1 구분

    @Column(length = 3)
    private String rPersonType2;    // 예매인원2 구분

    @Column(length = 3)
    private String rPersonType3;    // 예매인원3 구분

    @Column(length = 3)
    private String rPersonType4;    // 예매인원4 구분

    @Column(length = 3)
    private String rPersonType5;    // 예매인원5 구분

    @Column(length = 3)
    private String rPersonType6;    // 예매인원6 구분

    @Column(nullable = false)
    private int rPersonPrice1;      // 예매인원1 가격
    private int rPersonPrice2;      // 예매인원2 가격
    private int rPersonPrice3;      // 예매인원3 가격
    private int rPersonPrice4;      // 예매인원4 가격
    private int rPersonPrice5;      // 예매인원5 가격
    private int rPersonPrice6;      // 예매인원6 가격
}
