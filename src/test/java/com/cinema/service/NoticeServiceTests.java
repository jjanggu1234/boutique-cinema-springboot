package com.cinema.service;

import com.cinema.domain.Notice;
import com.cinema.dto.common.PageRequestDTO;
import com.cinema.dto.common.PageResponseDTO;
import com.cinema.dto.movie.NoticeDTO;
import com.cinema.repository.NoticeRepository;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class NoticeServiceTests {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void testRegister(){
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .nTitle("서비스테스트")
                .nContent("서비스내용")
                .nDate(LocalDate.of(2023,11,23))
                .build();
        Long nNum = noticeService.register(noticeDTO);
        System.out.println(nNum);
        assert nNum != null;
    }
@Test
    public void testSearch(){
        Long nNum = 20L;
        NoticeDTO noticeDTO = noticeService.get(nNum);
        System.out.println(noticeDTO);
}

@Test
    void modify(){
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .nNum(1L)
                .nTitle("수정해볼내용")
                .nContent("수정완료")
                .build();

        noticeService.modify(noticeDTO);

}

@Test
    public void testPaging() {
    // PageRequestDTO 인스턴스 생성
    PageRequestDTO pageRequestDTO = new PageRequestDTO();
    pageRequestDTO.setPage(1); // 페이지 번호 설정
    pageRequestDTO.setSize(10); // 페이지당 항목 수 설정

    // 서비스에서 페이징된 공지사항 요청
    PageResponseDTO<NoticeDTO> response = noticeService.getNotices(pageRequestDTO);

    // 응답 확인
    System.out.println(response);

}

}
