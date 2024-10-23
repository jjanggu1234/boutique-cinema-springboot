package com.cinema.dto.reservation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
  @Min(value = 0, message = "관람 후기 평점은 최소 0 이상이어야 합니다.")
  @Max(value = 10, message = "관람 후기 평점은 10을 초과할 수 없습니다.")
  private Integer reviewRating;

  @Size(max = 100, message = "관람 후기는 최대 100자까지 입력할 수 있습니다.")
  private String reviewContent;
}
