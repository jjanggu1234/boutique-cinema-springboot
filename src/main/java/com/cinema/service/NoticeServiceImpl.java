package com.cinema.service;


import com.cinema.domain.Notice;
import com.cinema.dto.common.PageRequestDTO;
import com.cinema.dto.common.PageResponseDTO;
import com.cinema.dto.movie.NoticeDTO;
import com.cinema.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public Long register(NoticeDTO noticeDTO) {
        Notice notice = Notice.builder()
                .nTitle(noticeDTO.getNTitle())
                .nContent(noticeDTO.getNContent()) // 수정된 부분
                .nDate(noticeDTO.getNDate())
                .build();
        // Notice 저장
        Notice savedNotice = noticeRepository.save(notice);
        return savedNotice.getNNum();
    }

    @Override
    public NoticeDTO get(Long nNum) {
        Notice notice = noticeRepository.findById(nNum).orElse(null);
        if (notice != null) {
            return NoticeDTO.builder()
                    .nNum(notice.getNNum())
                    .nTitle(notice.getNTitle())
                    .nContent(notice.getNContent()) // 수정된 부분
                    .nDate(notice.getNDate())
                    .build();
        }
        return null;
    }

    @Override
    public void modify(NoticeDTO noticeDTO) {
        Notice notice = noticeRepository.findById(noticeDTO.getNNum()).orElse(null);
        if (notice == null) {
            throw new RuntimeException("Notice not found"); // 예외 처리 추가
        }
        notice.changeNTitle(noticeDTO.getNTitle());
        notice.changeNContent(noticeDTO.getNContent());
        notice.changNDate(noticeDTO.getNDate());

        noticeRepository.save(notice);
    }

    @Override
    public void delete(Long nNum) {
        noticeRepository.deleteById(nNum);
    }

    @Override
    public PageResponseDTO<NoticeDTO>getNotices(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() -1 , pageRequestDTO.getSize());
        Page<Notice> result = noticeRepository.findAll(pageable);

        List<NoticeDTO> noticeDTO = result.getContent().stream()
                .map( notice -> NoticeDTO.builder()
                        .nNum(notice.getNNum())
                        .nTitle(notice.getNTitle())
                        .nContent(notice.getNContent())
                        .nDate(notice.getNDate())
                        .build())
                .toList();
        return new PageResponseDTO<>(
                noticeDTO, result.getTotalElements(),pageRequestDTO.getPage(),pageRequestDTO.getSize());
    }
}

