package com.cinema.service;

import com.cinema.dto.movie.MovieDTO;

public interface MovieService {
    Long register (MovieDTO movieDTO);    //등록
    MovieDTO get(Long movieNum);          //조회
    void modify(MovieDTO movieDTO);       //수정
    void remove(Long movieNum);           //삭제
}
