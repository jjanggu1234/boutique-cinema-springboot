package com.cinema.service;

import com.cinema.domain.Movie;
import com.cinema.dto.movie.MovieDTO;
import com.cinema.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor  //생성자 자동 주입
public class MovieServiceImpl implements MovieService {

    //자동 주입대상은 final로 설정
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    @Override                               //데이터 등록
    public Long register(MovieDTO movieDTO) {
        Movie movie = modelMapper.map(movieDTO, Movie.class);
        Movie savedMovie = movieRepository.save(movie);

        return savedMovie.getMovieNum();
    }

    @Override                             //데이터 조회
    public MovieDTO get(Long movieNum) {
        java.util.Optional<Movie> result = movieRepository.findById(movieNum);
        Movie movie = result.orElseThrow();
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
        return movieDTO;
    }

    @Override                             // 데이터 수정
    public void modify(MovieDTO movieDTO) {
        Optional<Movie> result = movieRepository.findById(movieDTO.getMovieNum());
        Movie movie = result.orElseThrow();

        // 모든 필드 수정
        movie.setKorTitle(movieDTO.getKorTitle());
        movie.setEnTitle(movieDTO.getEnTitle());
        movie.setMovieDesc(movieDTO.getMovieDesc());
        movie.setRunTime(movieDTO.getRunTime());
        movie.setGenre(movieDTO.getGenre());
        movie.setPosterUrl(movieDTO.getPosterUrl());
        movie.setTrailerUrl(movieDTO.getTrailerUrl());
        movie.setDirector(movieDTO.getDirector());
        movie.setCast(movieDTO.getCast());
        movie.setRating(movieDTO.getRating());
        movie.setMovieStartDate(movieDTO.getMovieStartDate());
        movie.setMovieEndDate(movieDTO.getMovieEndDate());
        movie.setTheaterNum(movieDTO.getTheaterNum());
        movie.setRound1(movieDTO.getRound1());
        movie.setRound2(movieDTO.getRound2());
        movie.setRound3(movieDTO.getRound3());
        movie.setRound4(movieDTO.getRound4());
        movie.setRound5(movieDTO.getRound5());
        movie.setRoundTime1(movieDTO.getRoundTime1());
        movie.setRoundTime2(movieDTO.getRoundTime2());
        movie.setRoundTime3(movieDTO.getRoundTime3());
        movie.setRoundTime4(movieDTO.getRoundTime4());
        movie.setRoundTime5(movieDTO.getRoundTime5());
        movie.setRegDate(movieDTO.getRegDate());

        // 데이터베이스에 저장
        movieRepository.save(movie);
    }

    @Override                           //데이터 삭제
    public void remove(Long movieNum) {
        movieRepository.deleteById(movieNum);
    }
}
