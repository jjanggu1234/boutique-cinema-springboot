package com.cinema.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {   //dto페키지에 페이지 번호나 사이즈 등을 처리하기 위한 클래스

    @Builder.Default
    private int page = 1;     //페이지 번호

    @Builder.Default
    private int size = 10;    //페이지당 항목 수
}
