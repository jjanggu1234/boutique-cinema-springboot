package com.cinema.dto.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResponseDTO<T> {
    private List<T> content;    // 데이터 목록
    private long totalElements;  // 총 항목 수
    private int currentPage;     // 현재 페이지 번호
    private int pageSize;        // 페이지당 항목 수
}