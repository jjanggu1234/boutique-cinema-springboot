package com.cinema.controller;




import com.cinema.dto.movie.NoticeDTO;
import com.cinema.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 등록
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping// POST 메소드 처리
    public ResponseEntity<Long> createNotice(@RequestBody NoticeDTO noticeDTO) {
        Long savedId = noticeService.register(noticeDTO); //공지사항 등록
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId); // 생성된 ID 반환
    }
    // 특정 공지사항 조회
    @GetMapping("/{nNum}") //URL 에서 nNum 을 경로 변수로 선언
    public ResponseEntity<NoticeDTO> getNotice(@PathVariable Long nNum) {
        NoticeDTO noticeDTO = noticeService.get(nNum); //공지사항 조회
        if (noticeDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //공지사항이 없으면
        }
        return ResponseEntity.ok(noticeDTO); //공지사항 반환
    }

    //전체 공지사항 조회
    @GetMapping("/list") //공지사항 목록 조회
    public Page<NoticeDTO>getNotices(
            @RequestParam(defaultValue = "1")int page, //페이지 번호
            @RequestParam(defaultValue = "10")int size, //페이지 크기
            @RequestParam(required = false) String searchCondition) //검색조건
        throws Exception {
      // 페이지 번호가 1보다 작은 경우 1로 설정
    if (page < 1) {
        page = 1; // 기본값으로 설정
    }
    Pageable pageable = PageRequest.of(page - 1, size); // Pageable 객체 생성

    if (searchCondition == null) {
        // 검색 조건이 없을 경우 모든 공지사항 조회
        return noticeService.getFindAll(pageable);
    } else {
        // 검색 조건이 있을 경우 제목으로 공지사항 검색
        return noticeService.findByTitle(searchCondition, pageable);
    }
}

  @GetMapping("/list/latest") //최신 공지사항 목록 조회
  public Page<NoticeDTO>getLatestNotices(
          @RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10") int size)
       throws Exception {
        Pageable pageable = PageRequest.of(page - 1, size);
        return noticeService.getNoticesLatest(pageable);
  }
@GetMapping("/list/earliest") // 오래된 공지사항 목록 조회
public Page<NoticeDTO>getEarliestNotices(
        @RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "10") int size
) throws Exception {
        Pageable pageable = PageRequest.of(page - 1, size);
        return noticeService.getNoticesEarliest(pageable);
}

    // 공지사항 수정
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{nNum}")
    public ResponseEntity<Void> updateNotice(@PathVariable Long nNum, @RequestBody NoticeDTO noticeDTO) {
        noticeDTO.setNNum(nNum); // ID 설정
        try {
            noticeService.modify(noticeDTO);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 공지사항 삭제
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{nNum}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long nNum) {
        noticeService.delete(nNum);
        return ResponseEntity.noContent().build();
    }
}