package com.cinema.dto.qna;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Long qNum;           // 질문 문의글 번호
    private String mId;        // 회원 아이디
    private String qTitle;      // 질문 제목
    private String qContent;    // 질문 내용
    private String qStatus;     // 질문 답변상태
    private LocalDate qDate;         // 질문 작성일
}
