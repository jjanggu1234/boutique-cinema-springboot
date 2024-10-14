package com.cinema.service;


import com.cinema.dto.common.PageRequestDTO;
import com.cinema.dto.common.PageResponseDTO;
import com.cinema.dto.movie.NoticeDTO;

import java.util.List;


public interface NoticeService {
 Long register(NoticeDTO noticeDTO);
 NoticeDTO get(Long nNum);
 List<NoticeDTO> getAllNotices(PageRequestDTO pageRequestDTO); // 수정
 void modify(NoticeDTO noticeDTO);
 void delete(Long nNum);
}
