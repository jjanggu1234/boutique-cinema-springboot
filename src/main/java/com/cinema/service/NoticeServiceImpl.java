package com.cinema.service;


import com.cinema.domain.Notice;
import com.cinema.dto.movie.NoticeDTO;
import com.cinema.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {


    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(NoticeDTO noticeDTO) {
        Notice notice = Notice.builder()
                .nTitle(noticeDTO.getNTitle())
                .nContent(noticeDTO.getNContent()) // 수정된 부분
                .nDate(LocalDate.now())
                .build();
        // Notice 저장
        Notice savedNotice = noticeRepository.save(notice);
        return savedNotice.getNNum();
    }

   @Override
    public Page<NoticeDTO> findByTitle(String title, Pageable pageable) {
        String searchPattern = "%" + title + "%";
        return noticeRepository
            .findByNTitle(searchPattern, pageable)
            .map(notice -> modelMapper.map(notice, NoticeDTO.class));
    }

    @Override
    public Page<NoticeDTO>getFindAll(Pageable pageable) throws Exception {
        return noticeRepository
                .findAll(pageable).map(notice ->
                        modelMapper.map(notice, NoticeDTO.class));
    }

    @Override
    public Page<NoticeDTO> getNoticesLatest(Pageable pageable) throws Exception {
        return noticeRepository
            .findLatestByDate(pageable)
            .map(notice -> modelMapper.map(notice, NoticeDTO.class));
    }

      @Override
      public Page<NoticeDTO> getNoticesEarliest(Pageable pageable)throws Exception {
        return noticeRepository
            .findEarliestByDate(pageable)
            .map(notice -> modelMapper.map(notice, NoticeDTO.class));
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
        noticeRepository.save(notice);
    }

    @Override
    public void delete(Long nNum) {
        noticeRepository.deleteById(nNum);
    }

}

