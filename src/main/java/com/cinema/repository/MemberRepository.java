package com.cinema.repository;

import com.cinema.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

                                                                // pk타입(mId) String
public interface MemberRepository extends JpaRepository<Member, String> {

    // 이름 또는 아이디에 키워드가 포함된 회원 검색
    List<Member> findByNameContainingOrIdContaining(String name, String id);
                                                                }
