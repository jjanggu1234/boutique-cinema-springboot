package com.cinema.controller;



import com.cinema.dto.common.PageRequestDTO;
import com.cinema.dto.movie.NoticeDTO;
import com.cinema.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 등록
    @PostMapping
    public ResponseEntity<Long> createNotice(@RequestBody NoticeDTO noticeDTO) {
        Long savedId = noticeService.register(noticeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    //전체 공지사항 조회
    @GetMapping
    public ResponseEntity<List<NoticeDTO>> getAllNotices(@RequestParam(defaultValue = "1") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        PageRequestDTO pageRequestDTO = new PageRequestDTO(page, size);
        List<NoticeDTO> notices = noticeService.getAllNotices(pageRequestDTO);
        return ResponseEntity.ok(notices);
    }

    // 특정 공지사항 조회
    @GetMapping("/{nNum}")
    public ResponseEntity<NoticeDTO> getNotice(@PathVariable Long nNum) {
        NoticeDTO noticeDTO = noticeService.get(nNum);
        if (noticeDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(noticeDTO);
    }

    // 공지사항 수정
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
    @DeleteMapping("/{nNum}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long nNum) {
        noticeService.delete(nNum);
        return ResponseEntity.noContent().build();
    }
}