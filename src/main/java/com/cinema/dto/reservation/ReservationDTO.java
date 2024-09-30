package com.cinema.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
  private String rNum; // 영화예매번호
  private String seatNum; // 영화예매 좌석번호
  private int paymentAmount; // 영화예매 결제금액

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss")
  private LocalDateTime rDate; // 영화예매일자

  private int isCanceled; // 영화예매 취소여부

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss")
  private LocalDateTime cancelDate; // 영화예매 취소일자

  private String reviewContent; // 관람후기내용
  private BigDecimal reviewRating; // 관람후기평점
  private String rPersonType1; // 예매인원1 구분
  private String rPersonType2; // 예매인원2 구분
  private String rPersonType3; // 예매인원3 구분
  private String rPersonType4; // 예매인원4 구분
  private String rPersonType5; // 예매인원5 구분
  private String rPersonType6; // 예매인원6 구분
  private int rPersonPrice1; // 예매인원1 가격
  private int rPersonPrice2; // 예매인원2 가격
  private int rPersonPrice3; // 예매인원3 가격
  private int rPersonPrice4; // 예매인원4 가격
  private int rPersonPrice5; // 예매인원5 가격
  private int rPersonPrice6; // 예매인원6 가격
}
