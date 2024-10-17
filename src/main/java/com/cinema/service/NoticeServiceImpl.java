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
    public List<NoticeDTO> getAllNotices(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize());
        List<Notice> notices = noticeRepository.findAll(pageable).getContent();

        return notices.stream()
                .map(notice -> NoticeDTO.builder()
                        .nNum(notice.getNNum())
                        .nTitle(notice.getNTitle())
                        .nContent(notice.getNContent())
                        .nDate(notice.getNDate())
                        .build())
                .toList();
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
        Notice notice = noticeRepository.findById(noticeDTO.getNNum()).orElseThrow(() ->
                new RuntimeException("Notice not found")); // Optional 처리

        // Notice 엔티티의 필드 값 변경
        notice.changeNTitle(noticeDTO.getNTitle());
        notice.changeNContent(noticeDTO.getNContent());
        notice.changeNDate(noticeDTO.getNDate());

        // save() 호출 없이 트랜잭션이 끝나면 자동으로 변경 사항이 반영됩니다.
    }

    @Override
    public void delete(Long nNum) {
        noticeRepository.deleteById(nNum);
    }

}

