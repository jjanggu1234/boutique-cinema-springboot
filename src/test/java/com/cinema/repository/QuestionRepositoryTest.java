package com.cinema.repository;

import com.cinema.domain.Member;
import com.cinema.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MemberRepository memberRepository;

//    @Test
//    public void insertTest() {
//        // Member를 데이터베이스에서 조회합니다.
//        Member member = memberRepository.findById("qqq123123") // ID가 "testUser"인 경우
//                .orElseThrow(() -> new RuntimeException("Member not found"));
//
//        Question question = Question.builder()
//                .M_ID(member) // Member 객체 설정
//                .qTitle("1대1문의 질문 제목 테스트")
//                .qDate(LocalDate.now())
//                .qContent("1대1 문의 질문 내용 등록 테스트")
//                .qStatus("답변상태 테스트")
//                .build();
//
//        questionRepository.save(question);
//    }

//    @Test                   //데이터 조회 테스트
//    public void ReadTest() {
//        Long qNum = 2L;
//        java.util.Optional<Question> result = questionRepository.findById(qNum);
//        Question question = result.orElseThrow(() -> new RuntimeException("Question not found"));
//
//    }

//    @Test
//    public void ModifyTest(){
//        Long qNum = 2L;
//        java.util.Optional<Question> result = questionRepository.findById(qNum);
//        Question question = result.orElseThrow(() -> new RuntimeException("Question not found"));
//
//        // 수정할 내용 작성
//        question.setQTitle("수정된 제목"); // 제목 수정
//        question.setQContent("수정된 내용"); // 내용 수정
//        question.setQStatus("수정된 답변 상태"); // 답변 상태 수정
//        question.setQDate(LocalDate.now()); // 날짜 수정 (필요에 따라)
//
//        questionRepository.save(question);
//    }

//    @Test
//    public void removeTest(){
//        Long qNum = 2L;
//
//        questionRepository.deleteById(qNum);
//    }
}