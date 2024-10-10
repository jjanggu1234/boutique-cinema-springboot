package com.cinema.service;


import com.cinema.dto.common.PageRequestDTO;
import com.cinema.dto.common.PageResponseDTO;
import com.cinema.dto.movie.NoticeDTO;


public interface NoticeService {
 Long register(NoticeDTO noticeDTO);
 NoticeDTO get(Long nNum);
 void modify(NoticeDTO noticeDTO);
 void delete(Long nNum);

 //페이징 처리 메서드 추가
 PageResponseDTO<NoticeDTO> getNotices(PageRequestDTO pageRequestDTO);
}
