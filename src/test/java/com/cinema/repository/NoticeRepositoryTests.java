package com.cinema.repository;


import com.cinema.domain.Notice;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
class NoticeRepositoryTests {
    private static final Logger log = LoggerFactory.getLogger(NoticeRepositoryTests.class);
    @Autowired
    private NoticeRepository noticeRepository;

  @Test
    public void testInsert() {
      for (int i = 0; i < 25; i++) {
          Notice notice = Notice.builder()
                  .nTitle("공지사항 제목" + i)
                  .nContent("공지사항 내용" + i)
                  .nDate(LocalDate.of(2023,12,31))
                  .build();

          noticeRepository.save(notice);
      }
  }
  @Test
    public void testRead(){
      //존재하는 번호로 확인
      Long nNum = 1L;
      java.util.Optional<Notice> result = noticeRepository.findById(nNum);
      Notice notice = result.orElse(null);
      log.info(notice.toString());
  }
  @Test
    public void testModify() {
      Long nNum = 1L;
      java.util.Optional<Notice> result = noticeRepository.findById(nNum);
      Notice notice = result.orElse(null);
      notice.changeNTitle("수정된 제목");
      notice.changeNContent("수정된 내용");
      noticeRepository.save(notice);
  }
  @Test
    public void testDelete() {
      Long nNum = 1L;
      noticeRepository.deleteById(nNum);
  }
  @Test
    public void testPaging(){
      Pageable pageable = PageRequest.of(0,10, Sort.by("nNum").descending());
        Page<Notice> result = noticeRepository.findAll(pageable);
        result.getContent().forEach(notice -> log.info(notice.toString()));
  }
}
