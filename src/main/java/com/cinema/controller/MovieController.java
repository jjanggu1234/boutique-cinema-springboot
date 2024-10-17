package com.cinema.controller;

import com.cinema.dto.movie.MovieDTO;
import com.cinema.service.MovieService;
import com.cinema.util.CustomFileUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/movie")
public class MovieController {
  private final CustomFileUtil fileUtil;
  private final MovieService movieService;

  @PostMapping // 영화 등록
  public Map<String, String> register(
      @ModelAttribute MovieDTO movieDTO, @RequestParam(value = "file") MultipartFile file)
      throws Exception {

    // 파일 확장자 검증
    String originalFilename = file.getOriginalFilename();
    if (originalFilename != null && !isValidImageFile(originalFilename)) {
      return Map.of("error", "Invalid file type. Only JPG and PNG are allowed.");
    }

    // 파일을 저장하고 저장된 이름을 가져옴
    String savedFileName = fileUtil.saveFile(file);
    movieDTO.setPosterUrl(savedFileName); // 포스터 URL에 저장된 파일 이름 설정

    // 영화 정보를 데이터베이스에 저장
    Long movieNum = movieService.register(movieDTO); // 등록 후 영화 번호 반환

    // 성공 메시지 반환
    return Map.of(
        "message",
        "Movie registered successfully",
        "movieNum",
        String.valueOf(movieNum),
        "fileName",
        savedFileName);
  }

  @GetMapping("/{movieNum}") // 영화 상세 조회
  public MovieDTO getMovie(@PathVariable Long movieNum) throws Exception {
    return movieService.get(movieNum);
  }

  @GetMapping("/list") // 영화 목록 조회 (전체 목록)
  public Page<MovieDTO> getMovies(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String searchCondition)
      throws Exception {

    Pageable pageable = PageRequest.of(page - 1, size);

    if (searchCondition == null) {
      // 검색 조건이 없을 경우 모든 영화를 반환
      return movieService.getfindAll(PageRequest.of(page - 1, size));
    } else {
      // 검색 조건이 있을 경우 해당 조건으로 검색
      return movieService.findByKorTitle(searchCondition, PageRequest.of(page - 1, size));
    }
  }

  @GetMapping("/list/latest") // 최신순 영화 목록 조회
  public Page<MovieDTO> getLatestMovies(
      @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size)
      throws Exception {

    Pageable pageable = PageRequest.of(page - 1, size);
    return movieService.getMoviesLatestByDate(pageable);
  }

  @GetMapping("/list/earliest") // 오래된 순 영화 목록 조회
  public Page<MovieDTO> getEarliestMovies(
      @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size)
      throws Exception {

    Pageable pageable = PageRequest.of(page - 1, size);
    return movieService.getMoviesEarliestByDate(pageable);
  }

  @PutMapping("/{movieNum}") // 영화 수정
  public ResponseEntity<Void> modifyMovie(
      @PathVariable Long movieNum,
      @ModelAttribute MovieDTO movieDTO,
      @RequestParam(value = "file", required = false) MultipartFile file)
      throws Exception {

    // 파일 확장자 검증 (업데이트할 파일이 있을 경우)
    if (file != null && !file.isEmpty()) {
      String originalFilename = file.getOriginalFilename();
      if (originalFilename != null && !isValidImageFile(originalFilename)) {
        return ResponseEntity.badRequest().body(null); // 잘못된 파일 형식
      }
      // 파일을 저장하고 저장된 이름을 가져옴
      String savedFileName = fileUtil.saveFile(file);
      movieDTO.setPosterUrl(savedFileName); // 포스터 URL에 저장된 파일 이름 설정
    }

    // 영화 수정
    movieDTO.setMovieNum(movieNum); // DTO에 ID 세팅
    movieService.modify(movieDTO);

    return ResponseEntity.ok().build(); // 성공 응답
  }

  @DeleteMapping("/{movieNum}") // 영화 삭제
  public ResponseEntity<Void> removeMovie(@PathVariable Long movieNum) throws Exception {
    movieService.remove(movieNum);
    return ResponseEntity.noContent().build(); // 삭제 성공 응답
  }

  @GetMapping("/view/{posterUrl}")
  public ResponseEntity<Resource> viewFileget(@PathVariable String posterUrl) throws Exception {
    return fileUtil.getFile(posterUrl);
  }

  private boolean isValidImageFile(String filename) { // 파일 형식을 검증하는 메소드
    String lowerCaseName = filename.toLowerCase();
    return lowerCaseName.endsWith(".jpg")
        || lowerCaseName.endsWith(".jpeg")
        || lowerCaseName.endsWith(".png");
  }

  @GetMapping("/list/date")
  public List<MovieDTO> getMoviesByDate(
      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
      throws Exception {
    return movieService.getMoviesByDate(date);
  }
}
