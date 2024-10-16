package com.cinema.service;

import com.cinema.domain.Movie;
import com.cinema.dto.movie.MovieDTO;
import com.cinema.repository.MovieRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor // 생성자 자동 주입
public class MovieServiceImpl implements MovieService {

  // 자동 주입대상은 final로 설정
  private final ModelMapper modelMapper;
  private final MovieRepository movieRepository;

  @Override // 데이터 등록
  public Long register(MovieDTO movieDTO) throws Exception {
    Movie movie = modelMapper.map(movieDTO, Movie.class);
    Movie savedMovie = movieRepository.save(movie);
    return savedMovie.getMovieNum();
  }

  @Override // 데이터 조회
  public MovieDTO get(Long movieNum) throws Exception {
    java.util.Optional<Movie> result = movieRepository.findById(movieNum);
    Movie movie = result.orElseThrow();
    return modelMapper.map(movie, MovieDTO.class);
  }

  @Override // 데이터 수정
  public void modify(MovieDTO movieDTO) throws Exception {
    Optional<Movie> result = movieRepository.findById(movieDTO.getMovieNum());
    Movie movie = result.orElseThrow();

    // 모든 필드 수정
    modelMapper.map(movieDTO, movie);

    // 데이터베이스에 저장
    movieRepository.save(movie);
  }

  @Override // 데이터 삭제
  public void remove(Long movieNum) throws Exception {
    movieRepository.deleteById(movieNum);
  }

  @Override // 전체 목록 조회
  public Page<MovieDTO> getfindAll(Pageable pageable) throws Exception {
    return movieRepository.findAll(pageable).map(movie -> modelMapper.map(movie, MovieDTO.class));
  }

  @Override // 제목별 조회
  public Page<MovieDTO> findByKorTitle(String korTitle, Pageable pageable) throws Exception {
    String searchPattern = "%" + korTitle + "%"; // '%검색어%' 형태
    return movieRepository
        .findByKorTitle(searchPattern, pageable)
        .map(movie -> modelMapper.map(movie, MovieDTO.class));
  }

  @Override // 최신순 정렬
  public Page<MovieDTO> getMoviesLatestByDate(Pageable pageable) throws Exception {
    return movieRepository
        .findLatestByDate(pageable)
        .map(movie -> modelMapper.map(movie, MovieDTO.class));
  }

  @Override // 오래된 순 정렬
  public Page<MovieDTO> getMoviesEarliestByDate(Pageable pageable) throws Exception {
    return movieRepository
        .findEarliestByDate(pageable)
        .map(movie -> modelMapper.map(movie, MovieDTO.class));
  }

  @Override
  public List<MovieDTO> getMoviesByDate(LocalDate targetDate) throws Exception {
    List<Movie> movies = movieRepository.findMoviesByDate(targetDate);

    return movies.stream()
        .map(movie -> modelMapper.map(movie, MovieDTO.class)) // Entity -> DTO 변환
        .collect(Collectors.toList());
  }
}
