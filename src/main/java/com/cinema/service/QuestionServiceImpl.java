package com.cinema.service;

import com.cinema.domain.Member;
import com.cinema.domain.Question;
import com.cinema.dto.qna.QuestionDTO;
import com.cinema.repository.MemberRepository;
import com.cinema.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository questionRepository;
  private final ModelMapper modelMapper;
  private final MemberRepository memberRepository;

  @Override // 질문 등록
  public Long register(QuestionDTO questionDTO) throws Exception {
    // 회원 ID로 Member 조회
    Optional<Member> memberOpt = memberRepository.findById(questionDTO.getMId());
    Member member =
        memberOpt.orElseThrow(() -> new RuntimeException("Member not found")); // 회원이 없으면 예외 발생

    // 질문 객체 생성 및 저장
    Question question = modelMapper.map(questionDTO, Question.class);
    question.setMember(member); // 질문에 회원 정보 설정

    Question savedQuestion = questionRepository.save(question);
    return savedQuestion.getQNum();
  }

  @Override // 질문 상세
  public QuestionDTO get(Long qNum) throws Exception {
    Optional<Question> result = questionRepository.findById(qNum);
    Question question = result.orElseThrow(); // 질문이 없으면 예외 발생

    QuestionDTO saveDTO = modelMapper.map(question, QuestionDTO.class);

    saveDTO.setMId(question.getMember().getId());
    return saveDTO;
  }

  @Override // 질문 수정
  public void modify(QuestionDTO questionDTO) throws Exception {
    Optional<Question> result = questionRepository.findById(questionDTO.getQNum());
    Question question = result.orElseThrow(); // 질문이 없으면 예외 발생

    // 모든 필드 수정
    modelMapper.map(questionDTO, question);

    // 데이터베이스에 저장
    questionRepository.save(question);
  }

  @Override // 질문 삭제
  public void remove(Long qNum) throws Exception {
    questionRepository.deleteById(qNum);
  }

  @Override // 질문 리스트
  public Page<QuestionDTO> getfindAll(Pageable pageable) throws Exception {
    return questionRepository
        .findAll(pageable)
        .map(question -> modelMapper.map(question, QuestionDTO.class));
  }
}
