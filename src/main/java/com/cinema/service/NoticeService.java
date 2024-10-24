package com.cinema.service;

import com.cinema.dto.movie.NoticeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface NoticeService {
 Long register(NoticeDTO noticeDTO);
 NoticeDTO get(Long nNum);
 Page<NoticeDTO> getFindAll(Pageable pageable)throws Exception;
 Page<NoticeDTO> findByTitle(String nTitle, Pageable pageable); //제목
 Page<NoticeDTO> getNoticesLatest(Pageable pageable) throws Exception;
 Page<NoticeDTO> getNoticesEarliest(Pageable pageable) throws Exception;//최신순 정렬
 void modify(NoticeDTO noticeDTO);
 void delete(Long nNum);
}
